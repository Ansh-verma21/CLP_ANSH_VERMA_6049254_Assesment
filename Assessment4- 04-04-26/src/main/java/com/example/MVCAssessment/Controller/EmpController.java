package com.example.MVCAssessment.Controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.MVCAssessment.DTO.EmpDTO;
import com.example.MVCAssessment.service.EmpService;

import java.util.List;

@Controller
public class EmpController {

    @Autowired
    private EmpService service;

    @GetMapping("/viewall")
    public ModelAndView viewAll() {
        List<EmpDTO> list = service.getAllEmps();
        ModelAndView mv = new ModelAndView();
        mv.addObject("employees", list);
        mv.setViewName("viewall");
        return mv;
    }

    @GetMapping("/edit/{eid}")
    public ModelAndView showEditForm(@PathVariable Integer eid) {
        EmpDTO dto = service.getEmpById(eid);
        ModelAndView mv = new ModelAndView();
        if (dto == null) {
            mv.addObject("employees", service.getAllEmps());
            mv.addObject("errorMessage", "Employee not found with id- " + eid);
            mv.setViewName("viewall");
        } else {
            mv.addObject("emp", dto);
            mv.setViewName("edit");
        }
        return mv;
    }

    @PostMapping("/edit/{eid}")
    public ModelAndView saveEmployee(@PathVariable Integer eid,@Valid @ModelAttribute("emp") EmpDTO dto,BindingResult result) {
        if (result.hasErrors()) {
            dto.setEmpId(eid);
            ModelAndView mv = new ModelAndView();
            mv.addObject("emp", dto);
            mv.setViewName("edit");
            return mv;
        }
        service.updateEmp(eid, dto);
        return new ModelAndView("redirect:/viewall");
    }

    @GetMapping("/delete/{eid}")
    public ModelAndView deleteEmployee(@PathVariable Integer eid) {
        service.deleteEmp(eid);
        return new ModelAndView("redirect:/viewall");
    }
}