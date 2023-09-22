package jp.ac.u_aizu.ta_report_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jp.ac.u_aizu.ta_report_system.model.form.TaForm;
import jp.ac.u_aizu.ta_report_system.model.form.TaListForm;

@Service
public class TaListService {

  public List<TaListForm> createTaListForms() {

    final List<TaListForm> taListForms = new ArrayList<>();

    for (int i = 0; i < 4; i++) {
      appendTaListForm(taListForms, i);
    }
    return taListForms;
  }

  private void appendTaListForm(List<TaListForm> taListForms, int i) {

    final TaListForm taListForm = new TaListForm();
    taListForm.setNumStudentYear(33 + i);

    final List<TaForm> taForms = createTaForms(i);
    taListForm.setTaForms(taForms);

    taListForms.add(taListForm);
  }

  private List<TaForm> createTaForms(int i) {

    final List<TaForm> taForms = new ArrayList<>();

    for (int j = 0; j < 30; j++) {
      appendTaForm(taForms, i, j);
    }
    return taForms;
  }

  private void appendTaForm(List<TaForm> taForms, int i, int j) {

    final TaForm taForm = new TaForm();
    final long id = (long) 1200000 + 10000 * i + j;
    taForm.setAssistantId("s" + id);
    taForm.setFirstName("Ryo");
    taForm.setLastName("Kurihara");

    taForms.add(taForm);
  }
}
