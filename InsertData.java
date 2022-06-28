import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.Desktop;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Import Database Connection Class file
import code.DatabaseConnection;

// Servlet Name
@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {
        try {

            // Initialize the database
            Connection con = DatabaseConnection.initializeDatabase();

            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
            PreparedStatement st = con
                    .prepareStatement("insert into demo values(?, ?, ?, ?, ?)");

            // For the first parameter,
            // get the data using request object
            // sets the data to st pointer
             st.setString(1, request.getParameter("firstname"));

           
            st.setString(2, request.getParameter("lastname"));
            st.setString(3, request.getParameter("email"));
            st.setString(4, request.getParameter("username"));
            st.setString(5, request.getParameter("password"));
             

            // Execute the insert command using executeUpdate()
            // to make changes in database
            st.executeUpdate();

            // Close all the connections
            st.close();
            con.close();

            // Get a writer pointer 
            // to display the successful result
            PrintWriter out = response.getWriter();
            out.println("<html><body><b>Account has be created"
                    + "</b></body></html>");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("login.html"));
    }
}