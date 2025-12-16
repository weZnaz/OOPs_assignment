import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String sid = request.getParameter("sid");
        String dept = request.getParameter("dept");

        request.setAttribute("name", name);
        request.setAttribute("sid", sid);
        request.setAttribute("dept", dept);

        request.getRequestDispatcher("view.jsp").forward(request, response);
    }
}
