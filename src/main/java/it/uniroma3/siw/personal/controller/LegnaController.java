package it.uniroma3.siw.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.personal.controller.validator.CarneValidator;
import it.uniroma3.siw.personal.model.Carne;
import it.uniroma3.siw.personal.model.Legna;
import it.uniroma3.siw.personal.service.CarneService;
import it.uniroma3.siw.personal.service.LegnaService;

@Controller
public class LegnaController {
	
	
	
	@Autowired
	private LegnaService legnaService;
	@Autowired
	private CarneValidator artistaValidator;
	
	@RequestMapping(value="/legni", method = RequestMethod.GET)
    public String getLegna(Model model) {
    	model.addAttribute("artisti", this.legnaService.tutti());
        return "legna/legni";
    }
	
	@RequestMapping(value="/legniA", method = RequestMethod.GET)
    public String getLegnaA(Model model) {
    	model.addAttribute("legni", this.legnaService.tutti());
        return "legna/legniA";
    }
	
	@RequestMapping(value = "/legna/{id}", method = RequestMethod.GET)
    public String getLegna(@PathVariable("id") Long id, Model model) {
		Legna legna = this.legnaService.legnaPerId(id);
    	model.addAttribute("legna", legna);
    	return "legna/legna";
    }
	
	@RequestMapping(value = "/legnaA/{id}", method = RequestMethod.GET)
    public String getLegnaA(@PathVariable("id") Long id, Model model) {
		Legna legna = this.legnaService.legnaPerId(id);
    	model.addAttribute("legna", legna);
    	return "legna/legnaA";
    }
    
    @RequestMapping(value="/legnaForm", method = RequestMethod.GET)
    public String getForm(Model model) {
    	model.addAttribute("legna", new Legna());
        return "legna/legnaForm";
    }
    
    @RequestMapping(value = "/legnaForm", method = RequestMethod.POST)
    public String newLegna(@ModelAttribute("legna") Legna legna, 
    									Model model, BindingResult bindingResult) {
    	    if (!bindingResult.hasErrors()) {
        	this.legnaService.inserisci(legna);
            model.addAttribute("legni", this.legnaService.tutti());
            return "admin/home";
        }
    	    return "legna/legnaForm";
    }
}
