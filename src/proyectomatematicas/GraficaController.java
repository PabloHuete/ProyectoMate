package proyectomatematicas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 *
 * @author
 */
public class GraficaController implements Initializable {
    @FXML
    private ChoiceBox criterioChoiceBox;
    
    @FXML
    private TextField aTextField;
    
    @FXML
    private TextField bTextField;
            
    @FXML
    private TextField cTextField;
            
    @FXML
    private TextField discriminanteTextField;
            
    @FXML
    private TextField EjeDeSimetriaTextField;
    
    @FXML
    private TextField verticeTextField;
            
    @FXML
    private TextField dominioTextField;
            
    @FXML
    private TextField codominioTextField;
            
    @FXML
    private TextField ambitoTextField;
            
    @FXML
    private TextField interseccionYTextField;
            
    @FXML
    private TextField x1TextField;
            
    @FXML
    private TextField x2TextField;
    
    @FXML
    private TextField crecienteTextField;
    
    @FXML
    private TextField decrecienteTextField;
    
    @FXML
    private TextField positivaTextField;
    
    @FXML
    private TextField negativaTextField;
    
    @FXML 
    private TextField rangoFuncionTextField;
    
    
    // Declaramos el "LineChart" donde dibujaremos la funcion
    @FXML private LineChart<Double, Double> graph;
    //@FXML private NumberAxis x;
    //@FXML private NumberAxis y;
    
    //Variables a,b y c
    private int a;
    private int b;
    private int c;
    
    @FXML 
    private void Solucionar(ActionEvent event){
        
        int tipofuncion = getTipoFuncion(String.valueOf(criterioChoiceBox.getValue()));
        switch (tipofuncion) {
            case 1:
                {
                    a = Integer.parseInt(aTextField.getText());
                    b = Integer.parseInt(bTextField.getText());
                    c = Integer.parseInt(cTextField.getText());
                    mostrarDatos(Double(a), Double(b), Double(c));//Se debe mandar el dato numérico con Decimales dobles
                    break;
                }
            case 2:
                {
                    a = Integer.parseInt(aTextField.getText());
                    b = Integer.parseInt(bTextField.getText());
                    c =0;
                    mostrarDatos(Double(a), Double(b), Double(c));//Se debe mandar el dato numérico con Decimales dobles
                    break;
                }
            case 3:
                {
                    a = Integer.parseInt(aTextField.getText());
                    c = Integer.parseInt(cTextField.getText());
                    b =0;
                    mostrarDatos(Double(a), Double(b), Double(c));//Se debe mandar el dato numérico con Decimales dobles
                    break;
                }
            default:
                break;
        }
    }
    
    /**
     * Este método le pasa los datos a los campos de texto 
     * @param a
     * @param b
     * @param c 
     */
    private void mostrarDatos(double a, double b, double c){
        
        double discriminante = getDiscriminante(a, b, c);
        discriminanteTextField.setText(String.valueOf(discriminante));
        
        double ejeDeSimetria = getEjeDeSimetria(a, b);
        EjeDeSimetriaTextField.setText(String.valueOf(ejeDeSimetria));
        
        double vertice = getVertice(a , discriminante);
        if (a>0){
           verticeTextField.setText("("+String.valueOf(ejeDeSimetria)+" , "+vertice+")"+" pto. Mínimo");
        }
        else{
            verticeTextField.setText("("+String.valueOf(ejeDeSimetria)+" , "+vertice+")"+" pto. Máximo");
        }
        
        dominioTextField.setText("Números Reales");
        
        codominioTextField.setText("Números Reales");
        
        crecienteTextField.setText(crece(a, ejeDeSimetria));
        
        decrecienteTextField.setText(decrece(a, ejeDeSimetria));
       
        ambitoTextField.setText(determinarAmbito(a , vertice));
        
        interseccionYTextField.setText(determinarInterseccionY(c));
        
        x1TextField.setText(getInterseccionesX(getInterseccionX1(a, b, discriminante)));
        
        x2TextField.setText(getInterseccionesX(getInterseccionX2(a, b, discriminante)));
        
        int r = Integer.parseInt(rangoFuncionTextField.getText());
        String cordenada1, cordenada2;
        for (double i = (r*-1.0); i <= r; i=i+0.25){
            double ordenada = (a*Math.pow(i, 2))+(b*i)+ c;
            //System.out.println("f(x)= "+ordenada);
            
            if (!"".equals(x1TextField.getText())){
                if (ordenada >= 0.0 || a>0){
                    cordenada1 = "( -infinito ,"+String.valueOf(getInterseccionX1(a, b, discriminante))+"),("+String.valueOf(getInterseccionX2(a, b, discriminante))+", +infinito)";
                    positivaTextField.setText(cordenada1);
                    
                    cordenada2 = "("+String.valueOf(getInterseccionX1(a, b, discriminante))+", 0),(0 ,"+String.valueOf(getInterseccionX2(a, b, discriminante))+")";
                    negativaTextField.setText(cordenada2);
                }
                else if(ordenada<=0.0 || a<0){
                    cordenada1 = "("+String.valueOf(getInterseccionX1(a, b, discriminante))+", 0),(0, "+String.valueOf(getInterseccionX2(a, b, discriminante))+")";
                    positivaTextField.setText(cordenada1);
                    
                    cordenada2 = "(- infinito, "+String.valueOf(getInterseccionX1(a, b, discriminante))+"),("+String.valueOf(getInterseccionX2(a, b, discriminante))+", +infinito)";
                    negativaTextField.setText(cordenada2);
                }
            }
            else {
                if(a>0){
                    positivaTextField.setText("(-infinito, +infinito)");
                    negativaTextField.setText("No es negativa");
                }
                else{
                    positivaTextField.setText("No es positiva");
                    negativaTextField.setText("(-infinito, +infinito)");
                }
                
            }    
        }
    }
    
    /**
     * Método que calcula el Discriminante
     * @param a
     * @param b
     * @param c
     * @return devuelve el valor del discriminante de la funcion
     */
    private double getDiscriminante(double a, double b, double c){
        double discriminante;
        
        discriminante = (Math.pow(b, 2)-(4*a*c));
        
        return discriminante;
    }
    
    /**
     * Método que calcula el Eje de Simetría
     * @param a
     * @param b
     * @return devuelve el eje de simetría
     */
    private double getEjeDeSimetria (double a, double b){
        double ejeDeSimetria;
        
        ejeDeSimetria = ((-1*b)/(2*a));
        
        return ejeDeSimetria;
    }
    
    /**
     * Método que calcula el valor de Y en el vértice
     * @param discriminante
     * @param a
     * @return devuelve el valor de Y en en Vértice
     */
    private double getVertice(double a, double discriminante){
        double vertice;
        
        vertice = -(discriminante)/(4*a);
        
        return vertice;
    }
    /**
     * Método que determina el Ámbito de la función
     * @param verticeY
     * @param a
     * @return devuelve un String con el intervalo
     */
    private String determinarAmbito (double a ,double verticeY){
        String ambito;
        if (a > 0){
            ambito = "["+String.valueOf(verticeY)+" , + infinito) ";
            return ambito;
        }
        else if(a < 0){
           ambito = "("+"- infinito , "+String.valueOf(verticeY)+"]";
            return ambito;
        }
        return null;
    }
    /**
     * Método que determina la interseccion con el eje Y (0,c)
     * @param c
     * @return 
     */
    private String determinarInterseccionY(double c){
        String interseccionY;
        
        interseccionY = "(0 , "+String.valueOf(c)+")";
    
        return interseccionY;
               
    }
    /**
     * Método que transforma el valor de de x1 y x2 para mostrarlo
     * @param x1
     * @param x2
     * @return devuelve el intervalo de esas intersecciones
     */
    private String getInterseccionesX(float x){
        String intersecciones;
        
        intersecciones = "("+String.valueOf(x)+" , 0)";
        
        return intersecciones;
    }
    /**
     * Método que calcula el valor de x1 através de la fórmula general
     * @param a
     * @param b
     * @param discriminante
     * @return x1
     */
    private float getInterseccionX1(double a, double b, double discriminante){
        float interseccion;
        
        interseccion = (float) (((-b)-Math.sqrt(discriminante))/(2*a));
        
        return interseccion;
    }
    
    /**
     * Método que calcula el valor de x2 através de la fórmula general
     * @param a
     * @param b
     * @param discriminante
     * @return x2
     */
    private float getInterseccionX2(double a, double b, double discriminante){
        float interseccion;
        
        interseccion = (float) (((-b)+Math.sqrt(discriminante))/(2*a));
        
        return interseccion;
    }
    
    private String crece (double a, double ejeDeSimetria){
        String variacion;
        if (a>0){
            variacion = "("+ejeDeSimetria+" , + infinito)";
        }
        else{
            variacion = "( -infinito , "+ejeDeSimetria+")";
        }
        return variacion;
    }
    
    private String decrece(double a, double ejeDeSimetria){
        String variacion;
        if (a>0){
            variacion = "( -infinito , "+ejeDeSimetria+")";
        }
        else{
            variacion = "("+ejeDeSimetria+" , + infinito)";
        }
        return variacion;
    }
     /**
     * Método que devuelve tipo de función a analizar
     * @param tipo
     * @return tipo de funcion
     */
    private int getTipoFuncion(String tipo){
        switch (tipo){
            case "ax^2+bx+c":
                return 1;
            
            case "ax^2+bx":
                return 2;
                
            case "ax^2+c":
                return 3;
                
            default:
                return 0;
        }
            
    }
    
    ///////////////////////////////////////////////Parte de la Gráfica///////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Método que se ejecuta al pulsar el boton "Dibujar Funcion"
     * @param event 
     */
    @FXML private void Dibujar(ActionEvent event) {
        int grado = 2;
        int numero = Integer.parseInt(rangoFuncionTextField.getText());
        pintarGrafica(-numero, numero, grado);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializamos el rango sobre el que pintaremos la funcion.
        rangoFuncionTextField.setText("7");
        criterioChoiceBox.setValue("ax^2+bx+c");
        
        //Código que verfica la entrada de datos erróneos en los campos de texto
        rangoFuncionTextField.textProperty().addListener((observable, oldValue, newValue) -> {
        if (!newValue.matches("\\d*")) {
            rangoFuncionTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }   
        });
        
    }
    
    /**
     * Método que devuelve el grado de la función a dibujar
     * @param funcion
     * @return grado de la funcion
     */
    private static int getGradoFuncion(String funcion){
        switch (funcion) {
            case "x":
                return 1;
            case "x^2":
                return 2;
            case "x^3":
                return 3;
            case "x^4":
                return 4;
            default:
                return 5;
        }
    }
    /**
     * Método para convertir enteros (int) a números con decimales
     * @param entero
     * @return 
     */
    public double Double (int entero){
        return (Double.parseDouble(String.valueOf(entero)));
    }
     
    /**
     * Método para pintar la gráfica
     * @param min
     * @param max
     * @param grado 
     */
    private void pintarGrafica (int min, int max, int grado){
        
        // Creamos un ObservableList para guardar los puntos que pintaremos en la gráfica
        ObservableList<XYChart.Series<Double, Double>> lineChartData = FXCollections.observableArrayList();
        
        // Instanciamos un punto a pintar
        LineChart.Series<Double, Double> series = new LineChart.Series<>();
        //LineChart.Series<Double, Double> ejeX = new LineChart.Series<>();
        //LineChart.Series<Double, Double> ejeY = new LineChart.Series<>();
        
        // Imprimimos la función que vamos a pintar como etiqueta
        series.setName("f(x^"+grado+")");
        //ejeX.setName("Eje X");
        //ejeY.setName("Eje Y");
        
        
        //Se pinta el eje x
//        for (int x = min; x >= 0; x--){
//            ejeX.getData().add(new XYChart.Data<>(x*-1.0,0.0));
//        }
//        for (int x =1; x<=max; x++){
//            ejeX.getData().add(new XYChart.Data<>(Double(x),0.0));
//        }
        
        //Se pinta el eje Y
//        for(int y = min; y >= 0; y--){
//            ejeY.getData().add(new XYChart.Data<>(0.0,y*-1.0));
//        }
//        for(int y=1; y <= max; y++){
//            ejeY.getData().add(new XYChart.Data<>(0.0,Double(y)));
//        }
        
        a = Integer.parseInt(aTextField.getText());
        b = Integer.parseInt(bTextField.getText());
        c = Integer.parseInt(cTextField.getText());
     
        
        for (double i = min; i <= max; i=i+0.5){
            double ordenada = (a*Math.pow(i, grado))+(b*i)+ c;
            series.getData().add(new XYChart.Data<>(i, ordenada));
            
        }
        
        // Guardamos todos los puntos de la función que hemos obtenido
        lineChartData.addAll(series);

        // Si no se quiere pintar los puntos, poner a false
        graph.setCreateSymbols(true);
        
        
        // Ponemos los puntos en la gráfica
        graph.setData(lineChartData);
        graph.createSymbolsProperty();
    }
}
