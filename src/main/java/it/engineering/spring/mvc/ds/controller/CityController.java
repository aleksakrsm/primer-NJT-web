package it.engineering.spring.mvc.ds.controller;

import it.engineering.spring.mvc.ds.dto.CityDto;
import it.engineering.spring.mvc.ds.service.CityService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(path = "/city")
public class CityController {

    private LocalValidatorFactoryBean validator;
    
    private CityService cityService;


    @Autowired
    public CityController(LocalValidatorFactoryBean validator,@Qualifier(value = "cityServiceImpl") CityService cityService) {
        this.validator = validator;
        this.cityService = cityService;
    }
    

    @RequestMapping(path = {"", "/", "/add"}, method = RequestMethod.GET)
    public String add() {
        return "city-add";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request,
            @RequestParam(name = "number") String number,
            @RequestParam(name = "name") String name) {

        System.out.println("=========== save =========");

        try {
            Long code = Long.parseLong(number);
            CityDto cityDto = new CityDto(code, name);
            System.out.println(cityDto);

            cityService.save(cityDto);

            request.setAttribute("information", "Grad je uspesno sacuvan!");
            return "redirect:/city"; //uradi redirekciju
        } catch (Exception e) {
            request.setAttribute("exception", "Greska kod unosa!");
            return "city-add"; //forward
        }
    }

    @RequestMapping(path = "/saveDto", method = RequestMethod.POST)
    public String saveDto(HttpServletRequest request, CityDto cityDto) {

        System.out.println("=========== save: /saveDto =========");

        try {
            System.out.println(cityDto);
            cityService.save(cityDto);
            request.setAttribute("information", "Grad je uspesno sacuvan!");
            return "redirect:/city"; //uradi redirekciju
        } catch (Exception e) {
            request.setAttribute("exception", "Greska kod unosa!");
            return "city-add"; //forward
        }
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String viewAll(HttpServletRequest request) {
        try {
            request.setAttribute("cities", cityService.getAll());
        } catch (Exception ex) {
            request.setAttribute("exception", ex.getMessage());
        }
        return "city-list";
    }

    @RequestMapping(path = {"/details"}, method = RequestMethod.GET)
    public String details(HttpServletRequest request) {
        System.out.println("City number = " + request.getParameter("number"));
        CityDto cityDto = null;
        try {
            cityDto = cityService.findById(Long.parseLong(request.getParameter("number")));
        } catch (Exception ex) {
            request.setAttribute("exception", ex.getMessage());
        }
        if (cityDto == null) {
        } else {
            request.setAttribute("city", cityDto);
        }
        return "city-view";
    }

    @GetMapping(path = "/add-model")
    public ModelAndView addModel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cityDto", new CityDto(0L, "N/A"));
        modelAndView.setViewName("city-add-model");
        return modelAndView;
    }

    @PostMapping(path = "/save-model")
    public String saveModel(CityDto cityDto, RedirectAttributes redirectAttributes) throws Exception {
        System.out.println("=========================================");
        System.out.println("/save-model");
        System.out.println(cityDto);

        cityService.save(cityDto);
        redirectAttributes.addFlashAttribute("information", "Grad je uspesno sacuvan...");

        return "redirect:/city/add-model";
    }

    @GetMapping(path = "/add-binding")
    public String addBinding(@ModelAttribute(name = "cityDto") CityDto cityDto) {
        cityDto.setName("#");
        cityDto.setNumber(-1l);
        return "city-add-binding";
    }

    @PostMapping(path = "/save-binding-validation")
    public ModelAndView saveAndValidateBinding(@javax.validation.Valid @ModelAttribute("cityDto") CityDto cityDto, final BindingResult bindingResult,
			Model model) throws Exception {
        if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("city-add-binding");
			return modelAndView;
		}else{
			cityService.save(cityDto);
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("redirect:/city/add-binding");
			return modelAndView;
		}
    }

    @InitBinder
    public void initBinder(DataBinder binder) {
        if (binder.getTarget() instanceof CityDto) {
            binder.setValidator(validator);
        }
    }
}
