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
import it.uniroma3.siw.personal.model.Griglia;
import it.uniroma3.siw.personal.model.Legna;
import it.uniroma3.siw.personal.model.Pitmaster;
import it.uniroma3.siw.personal.service.CarneService;
import it.uniroma3.siw.personal.service.GrigliaService;
import it.uniroma3.siw.personal.service.LegnaService;
import it.uniroma3.siw.personal.service.PitmasterService;

@Controller
public class PitmasterController {
	
	
	
	@Autowired
	private PitmasterService pitmasterService;
	@Autowired
	private CarneValidator artistaValidator;
	@Autowired
	private GrigliaService grigliaService;
	
	@RequestMapping(value="/pitmasters", method = RequestMethod.GET)
    public String getPitmaster(Model model) {
    	model.addAttribute("pitmasters", this.pitmasterService.tutti());
        return "pitmaster/pitmasters";
    }
	
	@RequestMapping(value="/pitmastersA", method = RequestMethod.GET)
    public String getPitmasterA(Model model) {
    	model.addAttribute("pitmasters", this.pitmasterService.tutti());
        return "pitmaster/pitmastersA";
    }
	
	@RequestMapping(value = "/pitmaster/{id}", method = RequestMethod.GET)
    public String getPitmaster(@PathVariable("id") Long id, Model model) {
		Pitmaster pitmaster = this.pitmasterService.pitmasterPerId(id);
    	model.addAttribute("pitmaster", pitmaster);
    	model.addAttribute("griglia", pitmaster.getGriglia());
    	return "pitmaster/pitmaster";
    }
	
	@RequestMapping(value = "/pitmasterA/{id}", method = RequestMethod.GET)
    public String getPitmasterA(@PathVariable("id") Long id, Model model) {
		Pitmaster pitmaster = this.pitmasterService.pitmasterPerId(id);
    	model.addAttribute("pitmaster", pitmaster);
    	model.addAttribute("griglia", pitmaster.getGriglia());
    	return "pitmaster/pitmasterA";
    }
    
    @RequestMapping(value="/pitmasterForm", method = RequestMethod.GET)
    public String getForm(Model model) {
    	model.addAttribute("pitmaster", new Pitmaster());
        return "pitmaster/pitmasterForm";
    }
    
    @RequestMapping(value = "/pitmasterForm", method = RequestMethod.POST)
    public String newPitmaster(@ModelAttribute("pitmaster") Pitmaster pitmaster, 
    									Model model, BindingResult bindingResult) {
    	    if (!bindingResult.hasErrors()) {
        	this.pitmasterService.inserisci(pitmaster);
            model.addAttribute("pitmaster", pitmaster);
            model.addAttribute("griglie", this.grigliaService.tutti());
            return "pitmaster/listaGriglie";
        }
    	    return "pitmaster/pitmasterForm";
    }
    
    @RequestMapping(value = "/pitmasterFormGriglia/{pid}/{gid}", method = RequestMethod.GET)
    public String getPitmasterGriglia(@PathVariable("pid") Long pid,@PathVariable("gid") Long gid, Model model) {
		Pitmaster pitmaster = this.pitmasterService.pitmasterPerId(pid);
		Griglia grigla= this.grigliaService.grigliaPerId(gid);
    	pitmaster.setGriglia(grigla);
    	this.pitmasterService.inserisci(pitmaster);
    	return "admin/home";
    }
}
