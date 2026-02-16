@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

    StudentService service = new StudentService();

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if ("insert".equals(action)) {

            String departmentName = request.getParameter("departmentName");
            String studentNumber = request.getParameter("studentNumber");

            service.insertStudent(departmentName, studentNumber);
            out.println("<h3>Student Inserted Successfully!</h3>");

        } else {

            List<Student> list = service.getAllStudents();

            out.println("<h2>Student List</h2>");
            for (Student s : list) {
                out.println(
                        s.getId() + " | " +
                                s.getDepartmentName() + " | " +
                                s.getStudentNumber() + "<br>"
                );
            }
        }
    }
}
