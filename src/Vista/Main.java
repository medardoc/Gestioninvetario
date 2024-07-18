/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Vista;

import Controlador.Controlador;
import Controlador.ControladorLogin;
import  Controlador.ControladorUsuario;


/**
 *
 * @author javie
 */
public class Main {


   
    public static void main(String args[]) {
        InterfazAdmin admin = new InterfazAdmin();
        InterfazLogin login = new InterfazLogin();
        VistaUsuario vista = new VistaUsuario();
       
        
        Controlador con = new Controlador(admin);
        ControladorUsuario conuser = new ControladorUsuario(vista);
       
        ControladorLogin conlo = new ControladorLogin(login, admin, vista);
        
        login.setVisible(true);
       
        
    }
    }
    

