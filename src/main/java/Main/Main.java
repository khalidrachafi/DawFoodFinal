/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import controllers.DetalleticketJpaController;
import controllers.ProductosJpaController;
import controllers.TicketsJpaController;
import controllers.TipoproductoJpaController;
import controllers.TpvJpaController;
import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import models.Detalleticket;
import models.Productos;
import models.Tickets;
import models.Tipoproducto;
import views.VentanaPrincipal;

/**
 *
 * @author krach
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws IllegalOrphanException, NonexistentEntityException {

        new VentanaPrincipal().setVisible(true);

    }

}
