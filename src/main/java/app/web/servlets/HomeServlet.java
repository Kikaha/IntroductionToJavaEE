package app.web.servlets;

import app.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private final String PATH_HOME = "C:\\Users\\Nukem\\Desktop\\Java_Web\\6_0_INTRODUCTION_TO_JAVA_EE\\lab_exercise\\src\\main\\webapp\\views\\home.html";

    private FileUtil fileUtil;

    @Inject
    public HomeServlet(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String html = fileUtil.readFile(PATH_HOME);
        resp.getWriter().println(html);
    }
}
