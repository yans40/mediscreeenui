package com.openclassrooms.clientmediscreenui.controller;

import com.openclassrooms.clientmediscreenui.beans.NoteBean;
import com.openclassrooms.clientmediscreenui.beans.PatientBean;
import com.openclassrooms.clientmediscreenui.proxies.MicroserviceDiabeteAssessmentProxy;
import com.openclassrooms.clientmediscreenui.proxies.MicroserviceNotesProxy;
import com.openclassrooms.clientmediscreenui.proxies.MicroservicePatientsProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MediscreenUiControllerTest {

    @Mock
    private MicroserviceNotesProxy notesProxy;

    @Mock
    private MicroserviceDiabeteAssessmentProxy diabeteAssessmentProxy;

    @Mock
    private MicroservicePatientsProxy patientsProxy;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private mediscreenUiController controller;

    @Before
    public void setUp() {
        // Définir le comportement attendu pour les méthodes des proxies
        List<PatientBean> patients = new ArrayList<>();
        when(patientsProxy.listDesPatients()).thenReturn(patients);

    }

    @Test
    public void testAccueilDisplay() {
        // Appeler la méthode à tester
        String viewName = controller.accueilDisplay(model);

        verify(patientsProxy).listDesPatients();
    }

    @Test
    public void testShowNewForm() {
        // Appeler la méthode à tester
        String viewName = controller.showNewForm(model);

    }

    @Test
    public void testCreatePatient() {
        // Appeler la méthode à tester
        String viewName = controller.createPatient(new PatientBean(), redirectAttributes);


        verify(patientsProxy).createPatient(any(PatientBean.class));


    }

    @Test
    public void testShowPatientUpdateForm() {
        // j'appelle la méthode à tester
        String viewName = controller.showPatientupdateForm(1L, model);

        verify(patientsProxy).recupererUnPatient(1L);

    }

    @Test
    public void testUpdatePatient() {
        // j'appelle la méthode à tester
        String viewName = controller.updatePatient(1L, new PatientBean(), redirectAttributes);

        verify(patientsProxy).updatePatient(eq(1L), any(PatientBean.class));


    }


    @Test
    public void testShowPatientNoteForm() {
        // j'appelle la méthode à tester
        String viewName = controller.showPatientNoteForm(1L, model);

        verify(patientsProxy).recupererUnPatient(1L);
        verify(notesProxy).getNotesByPatientId(1L);

    }

    @Test
    public void testSaveNote() {

        String viewName = controller.saveNote(1L, "Observation");

        verify(notesProxy).saveNote(any(NoteBean.class));

    }

    @Test
    public void testRiskEvaluator() {

        String result = controller.riskEvaluator(1L);

        verify(diabeteAssessmentProxy).evaluerrisque(1L);

    }


}