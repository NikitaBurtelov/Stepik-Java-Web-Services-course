package main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AllRequestsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        String key = request.getParameter("key");

        response.setContentType("text/html;charset=utf-8");

        if (key == null || key.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        pageVariables.put("key", key == null ? "" : key);

        System.out.println(key);

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("key", request.getParameterMap().toString());
        return pageVariables;
    }
}
