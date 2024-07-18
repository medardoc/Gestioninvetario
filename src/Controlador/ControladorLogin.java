package Controlador;

import Vista.InterfazAdmin;
import Vista.InterfazLogin;
import Vista.VistaUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author javie
 */
public class ControladorLogin implements ActionListener {

    InterfazLogin interfazlogin;
    InterfazAdmin admin1;
    VistaUsuario vistausuario1 ;
  
    public ControladorLogin() {
    }

     public ControladorLogin(InterfazLogin l, InterfazAdmin admin, VistaUsuario vistausuario ) {
        this.interfazlogin = l;
        this.admin1 = admin;
        this.vistausuario1 = vistausuario;
        // Login
        this.interfazlogin.irAdmin.addActionListener(this);
        this.interfazlogin.btnLoginInicio.addActionListener(this);
        this.interfazlogin.IrUser.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == interfazlogin.btnLoginInicio) {

            String usuario = interfazlogin.txtLoginUsuario.getText();
            String contraseña = new String(interfazlogin.txtLoginPassword.getPassword());
            String tipoUser = interfazlogin.jComboBoxLogin.getSelectedItem().toString();
            if (usuario.equals("Orlando") && contraseña.equals("123") && tipoUser.equals("Administrador")) {
                JOptionPane.showMessageDialog(null, "Bienvenido " + usuario);
                interfazlogin.dispose();
                admin1.setVisible(true);
                
            } else if (usuario.equals("User") && contraseña.equals("123") && tipoUser.equals("Usuario")) {
                JOptionPane.showMessageDialog(null, "Bienvenido usuario");
                vistausuario1.setVisible(true);
                interfazlogin.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
            }
        }
        if (e.getSource() == interfazlogin.irAdmin) {
            interfazlogin.dispose();
            admin1.setVisible(true);
            
        }
        if (e.getSource() == interfazlogin.IrUser) {
            vistausuario1.setVisible(true);
                interfazlogin.dispose();
            
        }
    }

}
