package app.web.servlets;

import app.domain.entities.Car;
import app.domain.models.binding.CarCreateBindingModel;
import app.domain.models.service.CarServiceModel;
import app.domain.repository.CarStorage;
import app.service.CarService;
import app.util.FileUtil;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CarCreateServlet extends HttpServlet {
    private static final String PATH_CREATE =
            "C:\\Users\\Nukem\\Desktop\\Java_Web\\6_0_INTRODUCTION_TO_JAVA_EE\\lab_exercise\\src\\main\\webapp\\views\\create.html";

    private FileUtil fileUtil;
    private ModelMapper modelMapper;
    private CarService carService;

    @Inject
    public CarCreateServlet(FileUtil fileUtil, CarCreateBindingModel carCreateBindingModel, CarStorage carStorage, ModelMapper modelMapper, CarService carService) {
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String html = fileUtil.readFile(PATH_CREATE);
        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
         CarCreateBindingModel binding = new CarCreateBindingModel();
         binding.setBrand(req.getParameter("brand"));
         binding.setModel(req.getParameter("model"));
         binding.setEngine(req.getParameter("engine"));
         binding.setYear(Integer.parseInt(req.getParameter("year")));

         this.carService.createCar(this.modelMapper.map(binding, CarServiceModel.class));

         resp.sendRedirect("/all");
    }
}
