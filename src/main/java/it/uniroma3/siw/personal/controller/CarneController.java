package it.uniroma3.siw.personal.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.personal.controller.validator.CarneValidator;
import it.uniroma3.siw.personal.model.Carne;
import it.uniroma3.siw.personal.model.Griglia;
import it.uniroma3.siw.personal.model.Legna;
import it.uniroma3.siw.personal.service.CarneService;
import it.uniroma3.siw.personal.service.GrigliaService;
import it.uniroma3.siw.personal.service.LegnaService;
import it.uniroma3.siw.personal.service.UserService;

@Controller
public class CarneController {
	
	
	
	@Autowired
	private CarneService carneService;
	@Autowired
	private LegnaService legnaService;
	@Autowired
	private GrigliaService grigliaService;
	@Autowired
	private CarneValidator carneValidator;
	@Autowired
	private UserService userService;
	
	
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
	
	@RequestMapping(value="/elCarni", method = RequestMethod.GET)
    public String getCarneEl(Model model) {
    	model.addAttribute("carni", this.carneService.tutti());
        return "carne/elCarni";
    }
	
	@RequestMapping(value = "/carne/{id}", method = RequestMethod.GET)
    public String getCarne(@PathVariable("id") Long id, Model model) {
		Carne carne = this.carneService.carnePerId(id);
		model.addAttribute("legna",carne.getLegna() );
		model.addAttribute("griglia",carne.getGriglia() );
    	model.addAttribute("carne", carne);
    	return "carne/carne";
    }
	
	@RequestMapping(value = "/elCarne/{id}", method = RequestMethod.GET)
    public String getCarneEl(@PathVariable("id") Long id, Model model) {
		Carne carne = this.carneService.carnePerId(id);
		model.addAttribute("legna",carne.getLegna() );
		model.addAttribute("griglia",carne.getGriglia() );
    	model.addAttribute("carne", carne);
    	return "carne/elCarne";
    }
	
	@RequestMapping(value = "/carneDel/{id}", method = RequestMethod.GET)
    public String carneDel(@PathVariable("id") Long id, Model model) {
		Carne carne = this.carneService.carnePerId(id);
		Griglia griglia = carne.getGriglia();
		griglia.getCarne().remove(carne);
		this.grigliaService.inserisci(griglia);
		this.carneService.elimina(carne);
    	return "admin/home";
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
		model.addAttribute("legna",carne.getLegna() );
    	model.addAttribute("carne", carne);
    	model.addAttribute("griglia",carne.getGriglia() );
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
    	   this.carneValidator.validate(carne, bindingResult);
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
    
	
	@PostMapping("/check/{cid}")
	public String check(Model model,@PathVariable("cid") Long cid) {

	    Carne carne = this.carneService.carnePerId(cid);
	    Object nome= (Object) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    model.addAttribute("legno",carne.getLegna() );
    	model.addAttribute("griglia", carne.getGriglia());
	    model.addAttribute("carne", carne);
	    if(carne.getUsers()==null) {
	    	ArrayList<Object> a= new ArrayList<>();
	    	carne.setUsers(a);
	    	int i= carne.getLikes();
		      i++;
		      carne.setLikes(i);
		      carne.getUsers().add(nome);
		      this.carneService.inserisci(carne);
	    }
	    else {

	    if (!carne.getUsers().contains(nome)) {
	      int i= carne.getLikes();
	      i++;
	      carne.setLikes(i);
	      carne.getUsers().add(nome);
	      this.carneService.inserisci(carne);
	    }
	    }
	    return "carne/carne";
	}
}

