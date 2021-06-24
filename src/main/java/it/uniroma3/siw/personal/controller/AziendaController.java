package it.uniroma3.siw.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.personal.controller.validator.AziendaValidator;
import it.uniroma3.siw.personal.controller.validator.CarneValidator;
import it.uniroma3.siw.personal.model.Azienda;
import it.uniroma3.siw.personal.model.Griglia;
import it.uniroma3.siw.personal.service.AziendaService;
import it.uniroma3.siw.personal.service.GrigliaService;

@Controller
public class AziendaController {
	
	
	
	@Autowired
	private AziendaService aziendaService;
	@Autowired
	private AziendaValidator aziendaValidator;
	
	@RequestMapping(value="/aziende", method = RequestMethod.GET)
    public String getAzienda(Model model) {
    	model.addAttribute("aziende", this.aziendaService.tutti());
        return "azienda/aziende";
    }
	
	@RequestMapping(value="/aziendeA", method = RequestMethod.GET)
    public String getAziendaA(Model model) {
    	model.addAttribute("aziende", this.aziendaService.tutti());
        return "azienda/aziendeA";
    }
	
	@RequestMapping(value = "/azienda/{id}", method = RequestMethod.GET)
    public String getAzienda(@PathVariable("id") Long id, Model model) {
		Azienda azienda = this.aziendaService.aziendaPerId(id);
    	model.addAttribute("griglie", azienda.getGriglie());
    	model.addAttribute("azienda", azienda);
    	return "azienda/azienda";
    }
	
	@RequestMapping(value = "/aziendaA/{id}", method = RequestMethod.GET)
    public String getAziendaA(@PathVariable("id") Long id, Model model) {
		Azienda azienda = this.aziendaService.aziendaPerId(id);
    	model.addAttribute("griglie", azienda.getGriglie());
    	model.addAttribute("azienda", azienda);
    	return "azienda/aziendaA";
    }
    
    @RequestMapping(value="/aziendaForm", method = RequestMethod.GET)
    public String getForm(Model model) {
    	model.addAttribute("azienda", new Azienda());
        return "azienda/aziendaForm";
    }
    
    @RequestMapping(value = "/aziendaForm", method = RequestMethod.POST)
    public String newAzienda(@ModelAttribute("azienda") Azienda azienda, 
    									Model model, BindingResult bindingResult) {
    	 this.aziendaValidator.validate(azienda, bindingResult);
    	    if (!bindingResult.hasErrors()) {
        	this.aziendaService.inserisci(azienda);
            model.addAttribute("aziende", this.aziendaService.tutti());
            return "admin/home";
        }
    	    return "azienda/aziendaForm";
    }
}
