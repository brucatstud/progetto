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
import it.uniroma3.siw.personal.controller.validator.GrigliaValidator;
import it.uniroma3.siw.personal.model.Azienda;
import it.uniroma3.siw.personal.model.Griglia;
import it.uniroma3.siw.personal.model.Pitmaster;
import it.uniroma3.siw.personal.service.AziendaService;
import it.uniroma3.siw.personal.service.GrigliaService;

@Controller
public class GrigliaController {
	
	
	
	@Autowired
	private GrigliaService grigliaService;
	@Autowired
	private AziendaService aziendaService;
	@Autowired
	private GrigliaValidator grigliaValidator;
	
	@RequestMapping(value="/griglie", method = RequestMethod.GET)
    public String getGriglia(Model model) {
    	model.addAttribute("griglie", this.grigliaService.tutti());
        return "griglia/griglie";
    }
	
	@RequestMapping(value="/grigliaA", method = RequestMethod.GET)
    public String getGrigliaA(Model model) {
    	model.addAttribute("griglie", this.grigliaService.tutti());
        return "griglia/griglieA";
    }
	
	@RequestMapping(value = "/griglia/{id}", method = RequestMethod.GET)
    public String getGriglia(@PathVariable("id") Long id, Model model) {
		Griglia griglia = this.grigliaService.grigliaPerId(id);
		model.addAttribute("Pitmasters",griglia.getPitmasters() );
    	model.addAttribute("carni", griglia.getCarne());
    	model.addAttribute("azienda", griglia.getAzienda());
    	return "griglia/griglia";
    }
	
	@RequestMapping(value = "/grigliaA/{id}", method = RequestMethod.GET)
    public String getGrigliaA(@PathVariable("id") Long id, Model model) {
		Griglia griglia = this.grigliaService.grigliaPerId(id);
		model.addAttribute("Pitmasters",griglia.getPitmasters() );
    	model.addAttribute("carni", griglia.getCarne());
    	model.addAttribute("griglia", griglia);
    	model.addAttribute("azienda", griglia.getAzienda());
    	return "griglia/grigliaA";
    }
    
    @RequestMapping(value="/grigliaForm", method = RequestMethod.GET)
    public String getForm(Model model) {
    	model.addAttribute("griglia", new Griglia());
        return "griglia/grigliaForm";
    }
    
    @RequestMapping(value = "/grigliaForm", method = RequestMethod.POST)
    public String newGriglia(@ModelAttribute("griglia") Griglia griglia, 
    									Model model, BindingResult bindingResult) {
    	 this.grigliaValidator.validate(griglia, bindingResult);
    	    if (!bindingResult.hasErrors()) {
        	this.grigliaService.inserisci(griglia);
            model.addAttribute("griglia", griglia);
            model.addAttribute("aziende", this.aziendaService.tutti());
            return "griglia/listaAziende";
        }
    	    return "griglia/grigliaForm";
    }
    
    @RequestMapping(value = "/grigliaFormAzienda/{aid}/{gid}", method = RequestMethod.GET)
    public String getAzienda(@PathVariable("aid") Long aid,@PathVariable("gid") Long gid, Model model) {
		Azienda azienda = this.aziendaService.aziendaPerId(aid);
		Griglia griglia= this.grigliaService.grigliaPerId(gid);
    	griglia.setAzienda(azienda);
    	azienda.getGriglie().add(griglia);
    	this.aziendaService.inserisci(azienda);
    	this.grigliaService.inserisci(griglia);
    	return "admin/home";
    }
    
    @RequestMapping(value="/elGriglie", method = RequestMethod.GET)
    public String elGriglia(Model model) {
    	model.addAttribute("griglie", this.grigliaService.tutti());
        return "griglia/elGriglie";
    }
    
    @RequestMapping(value = "/elGriglia/{id}", method = RequestMethod.GET)
    public String elGriglia(@PathVariable("id") Long id, Model model) {
		Griglia griglia = this.grigliaService.grigliaPerId(id);
		Azienda azienda = griglia.getAzienda();
		azienda.getGriglie().remove(griglia);
		this.aziendaService.inserisci(azienda);
		this.grigliaService.elimina(griglia);
    	return "admin/home";
    }
}
