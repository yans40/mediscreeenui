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

        // Autres configurations de mocks si nécessaire
    }

    @Test
    public void testAccueilDisplay() {
        // Appeler la méthode à tester
        String viewName = controller.accueilDisplay(model);

        // Vérifier les interactions et les résultats
        verify(patientsProxy).listDesPatients();
        // Vérifier d'autres interactions si nécessaire

        // Vérifier le résultat de la méthode (vue renvoyée)
        // Exemple : assertEquals("Accueil", viewName);
    }

    @Test
    public void testShowNewForm() {
        // Appeler la méthode à tester
        String viewName = controller.showNewForm(model);

        // Vérifier les interactions et les résultats
        // Exemple : assertEquals("patientForm", viewName);
    }

    @Test
    public void testCreatePatient() {
        // Appeler la méthode à tester
        String viewName = controller.createPatient(new PatientBean(), redirectAttributes);

        // Vérifier les interactions et les résultats
        verify(patientsProxy).createPatient(any(PatientBean.class));
        // Vérifier d'autres interactions si nécessaire

        // Vérifier le résultat de la méthode (vue renvoyée)
        // Exemple : assertEquals("redirect:/", viewName);
    }

    @Test
    public void testShowPatientUpdateForm() {
        // Appeler la méthode à tester
        String viewName = controller.showPatientupdateForm(1L, model);

        // Vérifier les interactions et les résultats
        verify(patientsProxy).recupererUnPatient(1L);
        // Vérifier d'autres interactions si nécessaire

        // Vérifier le résultat de la méthode (vue renvoyée)
        // Exemple : assertEquals("update", viewName);
    }

    @Test
    public void testUpdatePatient() {
        // Appeler la méthode à tester
        String viewName = controller.updatePatient(1L, new PatientBean(), redirectAttributes);

        // Vérifier les interactions et les résultats
        verify(patientsProxy).updatePatient(eq(1L), any(PatientBean.class));
        // Vérifier d'autres interactions si nécessaire

        // Vérifier le résultat de la méthode (vue renvoyée)
        // Exemple : assertEquals("redirect:/", viewName);
    }



    @Test
    public void testShowPatientNoteForm() {
        // Appeler la méthode à tester
        String viewName = controller.showPatientNoteForm(1L, model);

        // Vérifier les interactions et les résultats
        verify(patientsProxy).recupererUnPatient(1L);
        verify(notesProxy).getNotesByPatientId(1L);
        // Vérifier d'autres interactions si nécessaire

        // Vérifier le résultat de la méthode (vue renvoyée)
        // Exemple : assertEquals("noteForm", viewName);
    }

    @Test
    public void testSaveNote() {
        // Appeler la méthode à tester
        String viewName = controller.saveNote(1L, "Observation");

        // Vérifier les interactions et les résultats
        verify(notesProxy).saveNote(any(NoteBean.class));
        // Vérifier d'autres interactions si nécessaire

        // Vérifier le résultat de la méthode (vue renvoyée)
        // Exemple : assertEquals("redirect:/addNote/{id}", viewName);
    }

    @Test
    public void testRiskEvaluator() {
        // Appeler la méthode à tester
        String result = controller.riskEvaluator(1L);

        // Vérifier les interactions et les résultats
        verify(diabeteAssessmentProxy).evaluerrisque(1L);
        // Vérifier d'autres interactions si nécessaire

        // Vérifier le résultat de la méthode
        // Exemple : assertEquals("Risque élevé", result);
    }

    // Ajouter d'autres méthodes de test si nécessaire

}