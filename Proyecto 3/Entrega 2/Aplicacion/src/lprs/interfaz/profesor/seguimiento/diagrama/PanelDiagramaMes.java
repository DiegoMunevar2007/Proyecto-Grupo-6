package lprs.interfaz.profesor.seguimiento.diagrama;

import lprs.logica.learningPath.LearningPath;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;

public class PanelDiagramaMes extends JPanel {
    private static final String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private static int[] DIAS = {31,28,31,30,31,30,31,31,30,31,30,31};
    private static final String[] DIAS_SEMANA = {"Domingo","Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
    private int mes;
    private int año;
    private LearningPath learningPath;
    private int cantidadDiasAgregados;

    public PanelDiagramaMes(int mes, int año, LearningPath learningPath) {
        setLayout( new BorderLayout());
        if (mes == 2 && año % 4 == 0 && (año % 100 != 0 || año % 400 == 0)){
            DIAS[1] = 29;
        }
        this.mes = mes;
        this.año = año;
        this.learningPath = learningPath;
        cantidadDiasAgregados = 0;
        JLabel titulo = new JLabel(MESES[mes-1], SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);
        JPanel panelDiasSemana = crearPanelDiasSemanaTexto();
        add(panelDiasSemana, BorderLayout.WEST);
        JPanel panelDias = crearPanelMes(learningPath);
        add(panelDias, BorderLayout.CENTER);
    }

    public JPanel crearPanelMes(LearningPath learningPath){
        JPanel panelDias = new JPanel();
        panelDias.setLayout(new GridLayout(1,5,10,10));
        LocalDate fecha = LocalDate.of(año, mes, 1);
        int diaInicio = 1;
        int diaFin = DIAS[mes-1];
        int cantidadEspacios = calcularCantidadEspacios(1, mes, año);
        panelDias.add(crearSemanaVacia(cantidadEspacios, learningPath));

        diaInicio = cantidadDiasAgregados;
        while(diaInicio<diaFin){
            int diaFinSemana = Math.min(diaInicio + 6, diaFin-1);
            if (diaFinSemana ==diaFin){
                panelDias.add(crearPanelSemana(diaInicio, diaFinSemana, learningPath));
            }
            else{
                panelDias.add(crearPanelSemana(diaInicio, diaFinSemana, learningPath));
            }
            diaInicio = diaFinSemana + 1;
        }


        return panelDias;
    }

    public JPanel crearSemanaVacia(int cantidadEspacios, LearningPath learningPath){
        JPanel panelSemana = new JPanel();
        panelSemana.setLayout(new GridLayout(7, 1, 10, 10));
        for (int i = 0; i < cantidadEspacios; i++) {
            JLabel labelVacio = new JLabel(" ");
            labelVacio.setOpaque(true);
            labelVacio.setPreferredSize(new Dimension(20, 20));
            panelSemana.add(labelVacio);
        }
        for (int i = 1; i <= 7-cantidadEspacios; i++) {
            int cantidad = learningPath.getCantidadActividadesEnMes(año, mes).getOrDefault(i, 0);
            JLabel labelColor = new JLabel(" ");
            labelColor.setOpaque(true);
            labelColor.setBorder(new LineBorder(Color.BLACK));
            labelColor.setPreferredSize(new Dimension(20, 20));
            if (cantidad == 0) {
                labelColor.setBackground(Color.WHITE);
            } else if (cantidad > 0 && cantidad < 3) {
                labelColor.setBackground(Color.LIGHT_GRAY);
            } else if (cantidad >= 3 && cantidad < 10) {
                labelColor.setBackground(Color.GRAY);
            } else {
                labelColor.setBackground(Color.BLACK);
            }
            panelSemana.add(labelColor);
            cantidadDiasAgregados += 1;
        }
        return panelSemana;
    }



    public JPanel crearPanelSemana(int diaInicio, int diaFin, LearningPath learningPath) {
    JPanel panelSemana = new JPanel();
    panelSemana.setLayout(new GridLayout(7, 1, 10, 10));
    HashMap<Integer, Integer> actividadesPorDia = learningPath.getCantidadActividadesEnMes(año, mes);

    for (int i = diaInicio; i <= diaFin; i++) {
        int cantidad = actividadesPorDia.getOrDefault(i, 0);
        JLabel labelColor = new JLabel(" ");
        labelColor.setOpaque(true);
        labelColor.setBorder(new LineBorder(Color.BLACK));
        labelColor.setPreferredSize(new Dimension(20, 20));
        if (cantidad == 0) {
            labelColor.setBackground(Color.WHITE);
        } else if (cantidad > 0 && cantidad < 3) {
            labelColor.setBackground(Color.LIGHT_GRAY);
        } else if (cantidad >= 3 && cantidad < 10) {
            labelColor.setBackground(Color.GRAY);
        } else {
            labelColor.setBackground(Color.BLACK);
        }

        panelSemana.add(labelColor);
    }
    return panelSemana;
}

    public JPanel crearPanelDiasSemanaTexto(){
        JPanel panelDiasSemana = new JPanel();
        panelDiasSemana.setLayout(new GridLayout(7,1,10,10));
        for (int i = 0; i < DIAS_SEMANA.length; i++){
            JLabel dia = new JLabel(DIAS_SEMANA[i]);
            panelDiasSemana.add(dia);
        }
        return panelDiasSemana;
    }

    public int calcularCantidadEspacios(int diaInicio, int mes, int anio){
        int diaSemana = LocalDate.of(anio, mes, diaInicio).getDayOfWeek().getValue() % 7;
        return diaSemana;
    }
}
