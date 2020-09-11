package app.web.servlets;

import app.domain.models.view.CarViewModel;
import app.service.CarService;
import app.util.FileUtil;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/all")
public class AllServlet extends HttpServlet {
    private static final String ALL_PATH =
            "C:\\Users\\Nukem\\Desktop\\Java_Web\\6_0_INTRODUCTION_TO_JAVA_EE\\lab_exercise\\src\\main\\webapp\\views\\all.html";

    private FileUtil fileUtil;
    private ModelMapper modelMapper;
    private CarService carService;

    @Inject
    public AllServlet(FileUtil fileUtil, ModelMapper modelMapper, CarService carService) {
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = fileUtil.readFile(ALL_PATH);

        StringBuilder sb = new StringBuilder();
        List<CarViewModel> cars = this.carService.allCars()
                .stream().map(x->this.modelMapper.map(x,CarViewModel.class)).collect(Collectors.toList());

        for (CarViewModel x : cars) {
            sb.append(String.format(
                    "<li class=\"d-flex justify-content-around\">\n" +
                            " <div class=\"col-md-4 d-flex flex-column text-center mb-3\">\n" +
                            "   <p class=\"text-white text-center\">%s</p>\n" +
                            " <p class=\"text-white text-center\">%s</p>\n" +
                            " <p class=\"text-white text-center\">%s</p>\n" +
                            " <p class=\"text-white text-center\">%s</p>\n" +
                            " </div>\n" +
                            "</li>", x.getBrand(),x.getModel(),x.getEngine(),x.getYear()));
        }
        html = html.replace("{{replace}}",sb.toString());
        resp.getWriter().println(html);
    }
}
