package jp.ac.u_aizu.ta_report_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import jp.ac.u_aizu.ta_report_system.model.form.TaListForm;
import jp.ac.u_aizu.ta_report_system.service.TaListService;

@Controller
public class TaListController {

  @Autowired
  TaListService taListService;

  @GetMapping({ "/staff", "/staff/{user_id}" })
  public String show(@ModelAttribute @PathVariable(required = false, name = "user_id") Long userId,
      Model model) {

    final List<TaListForm> taListForms = taListService.createTaListForms();

    model.addAttribute("ta_list_forms", taListForms);
    return "ta_list";
  }
}
