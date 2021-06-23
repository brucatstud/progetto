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
import it.uniroma3.siw.personal.service.CarneService;
import it.uniroma3.siw.personal.service.GrigliaService;
import it.uniroma3.siw.personal.service.LegnaService;

@Controller
public class CarneController {
	
	
	
	@Autowired
	private CarneService carneService;
	@Autowired
	private LegnaService legnaService;
	@Autowired
	private GrigliaService grigliaService;
	@Autowired
	private CarneValidator artistaValidator;
	
	
	@RequestMapping(value="/carni", method = RequestMethod.GET)
    public String getCarne(Model model) {
    	model.addAttribute("carni", this.carneService.tutti());
        return "carne/carni";
    }
	
	@RequestMapping(value="/carniM", method = RequestMethod.GET)
    public String getCarneM(Model model) {
    	model.addAttribute("carni", this.carneService.tutti());
        return "carne/carniM";
    }
	
	@RequestMapping(value="/carniA", method = RequestMethod.GET)
    public String getCarneA(Model model) {
    	model.addAttribute("carni", this.carneService.tutti());
        return "carne/carniA";
    }
	
	@RequestMapping(value = "/carne/{id}", method = RequestMethod.GET)
    public String getCarne(@PathVariable("id") Long id, Model model) {
		Carne carne = this.carneService.carnePerId(id);
		model.addAttribute("legna",carne.getLegna() );
		model.addAttribute("griglia",carne.getGriglia() );
    	model.addAttribute("carne", carne);
    	return "carne/carne";
    }
	
	@RequestMapping(value = "/carneM/{id}", method = RequestMethod.GET)
    public String getCarnem(@PathVariable("id") Long id, Model model) {
		Carne carne = this.carneService.carnePerId(id);
		model.addAttribute("legni", this.legnaService.tutti());
    	model.addAttribute("carne", carne);
    	return "carne/listaLegni";
    }
	
	@RequestMapping(value = "/carneA/{id}", method = RequestMethod.GET)
    public String getCarneA(@PathVariable("id") Long id, Model model) {
		Carne carne = this.carneService.carnePerId(id);
		model.addAttribute("legno",carne.getLegna() );
    	model.addAttribute("carne", carne);
    	return "carne/carneA";
    }
    
    @RequestMapping(value="/carneForm", method = RequestMethod.GET)
    public String getForm(Model model) {
    	model.addAttribute("carne", new Carne());
        return "carne/carneForm";
    }
    
    @RequestMapping(value = "/carneForm", method = RequestMethod.POST)
    public String newCarne(@ModelAttribute("carne") Carne carne, 
    									Model model, BindingResult bindingResult) {
    	    if (!bindingResult.hasErrors()) {
        	this.carneService.inserisci(carne);
        	model.addAttribute("carne", carne);
            model.addAttribute("griglie", this.grigliaService.tutti());
            return "carne/listaGriglie";
        }
    	    return "carne/carneForm";
    }
    
    @RequestMapping(value="/carneFormLegna/{cid}/{lid}", method = RequestMethod.GET)
    public String getListaLegni(@PathVariable("cid") Long cid, @PathVariable("lid") Long lid,Model model) {
    	Carne carne = carneService.carnePerId(cid);
    	Legna legna = this.legnaService.legnaPerId(lid);
    	carne.setLegna(legna);
    	carneService.inserisci(carne);
        return "admin/home";
    }
    
    @RequestMapping(value="/carneFormGriglia/{cid}/{gid}", method = RequestMethod.GET)
    public String getListaGriglie(@PathVariable("cid") Long cid, @PathVariable("gid") Long gid,Model model) {
    	Carne carne = carneService.carnePerId(cid);
    	Griglia griglia = this.grigliaService.grigliaPerId(gid);
    	carne.setGriglia(griglia);
    	griglia.getCarne().add(carne);
    	grigliaService.inserisci(griglia);
    	carneService.inserisci(carne);
        return "admin/home";
    }
}
