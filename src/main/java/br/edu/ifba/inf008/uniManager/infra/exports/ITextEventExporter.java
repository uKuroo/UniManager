package br.edu.ifba.inf008.uniManager.infra.exports;

import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import br.edu.ifba.inf008.uniManager.domain.entities.events.AcademicFair;
import br.edu.ifba.inf008.uniManager.domain.entities.events.Event;
import br.edu.ifba.inf008.uniManager.domain.entities.events.Lecture;
import br.edu.ifba.inf008.uniManager.domain.entities.events.ShortCourse;
import br.edu.ifba.inf008.uniManager.domain.entities.events.Workshop;
import br.edu.ifba.inf008.uniManager.domain.entities.participants.Participant;
import br.edu.ifba.inf008.uniManager.domain.ports.exports.ICertificateExporter;
import br.edu.ifba.inf008.uniManager.utils.menu.MenuUtil;

public class ITextEventExporter implements ICertificateExporter<Event> {

    @Override
    public void export(Event event) {
        try {
            File dir = new File("files/reports");
            if (!dir.exists()) dir.mkdirs();

            String filename = "files/reports/" + event.getId() + "_report.pdf";
            
            PdfWriter writer = new PdfWriter(new FileOutputStream(filename));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            
            float pageHeight = pdf.getDefaultPageSize().getHeight();
            Paragraph title = new Paragraph("📋 "+event.getClass().getSimpleName()+" Report")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(18)
                .setBold();

            document.add(title);
            document.add(new Paragraph("Title: " + event.getTitle()));
            document.add(new Paragraph("Type: " + event.getType()));
            document.add(new Paragraph("Date: " + event.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            document.add(new Paragraph("Location: " + event.getLocal()));
            document.add(new Paragraph("Description: " + event.getDescription()));
            document.add(new Paragraph("Vacancy: " + event.getVacancy()));

            addSpecificparagraph(document, event);

            document.add(new Paragraph("\n"));
            
            document.setMargins(pageHeight / 2 - 50, 36, 36, 36);

            Paragraph center = new Paragraph("Participants")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(18)
                .setBold();

            document.add(center);

            Table table = new Table(new float[]{2, 4, 2, 4, 3, 3});
            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell(header("CPF"));
            table.addHeaderCell(header("Name"));
            table.addHeaderCell(header("Type"));
            table.addHeaderCell(header("Email"));
            table.addHeaderCell(header("Phone"));
            table.addHeaderCell(header("Birthdate"));

            for (Map.Entry<String, Participant> entry : event.getParticipants().entrySet()) {
                Participant p = entry.getValue();
                table.addCell(p.getCpf());
                table.addCell(p.getName());
                table.addCell(p.getType());
                table.addCell(p.getEmail());
                table.addCell(p.getphone());
                table.addCell(p.getBirthDate().toString());
            }

            document.add(table);
            document.close();
        } catch (Exception e) {
            MenuUtil.errorScreen(e.getMessage());
        }
    }

    private void addSpecificparagraph(Document document, Event event) {
        if(event instanceof AcademicFair){
            AcademicFair academicFair = (AcademicFair) event;
            document.add(new Paragraph("Number of Stands: "+ (academicFair.getNumberOfStands())));
            document.add(new Paragraph("Organizer "+ (academicFair.getOrganizer().getName())+" Cpf: "+ (academicFair.getOrganizer().getCpf())));
        }
        if(event instanceof Lecture){
            Lecture lecture = (Lecture) event;
            document.add(new Paragraph("Theme: "+ (lecture.getTheme())));
            document.add(new Paragraph("Speaker "+ (lecture.getSpeaker().getName())+" Cpf: "+ (lecture.getSpeaker().getCpf())));
        }
        if(event instanceof ShortCourse){
            ShortCourse shortCourse = (ShortCourse) event;
            document.add(new Paragraph("Subject: "+ (shortCourse.getSubject())));
            document.add(new Paragraph("Teacher: "+ (shortCourse.getTeacher().getName())+" | Cpf: "+ (shortCourse.getTeacher().getCpf())));
        }
        if(event instanceof Workshop){
            Workshop workshop = (Workshop) event;
            document.add(new Paragraph("Needs Material: "+ (workshop.needMaterial() == true ? "Yes" : "No")));
            document.add(new Paragraph("Instructor "+ (workshop.getInstructor().getName())+" Cpf: "+ (workshop.getInstructor().getCpf())));
        }
    }

    private Cell header(String text) {
        return new Cell()
            .add(new Paragraph(text))
            .setBackgroundColor(ColorConstants.LIGHT_GRAY)
            .setFontColor(ColorConstants.BLACK)
            .setBold();
    }

}
