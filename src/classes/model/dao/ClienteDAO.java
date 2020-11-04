/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.model.dao;

import classes.connection.ConnectionFactory;
import classes.model.bean.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author glope
 */
public class ClienteDAO {
    public void save(Cliente cliente) {

        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();

            if (cliente.getId() == null) {
                em.persist(cliente);
            } else {
                em.merge(cliente);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Cliente remove(Integer id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Cliente cliente = null;
        try {
            cliente = em.find(Cliente.class, id);
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "removido com sucesso");
        } catch (Exception e) {
            System.err.println(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return cliente;
    }

    public Cliente findById(Integer id) {

        EntityManager em = new ConnectionFactory().getConnection();
        Cliente cliente = null;

        try {
            cliente = em.find(Cliente.class, id);//passa a classe e o id

        } catch (Exception e) {
            System.err.println(e);

        } finally {
            em.close();

        }
        return cliente;

    }

    public List<Cliente> findAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<Cliente> clientes = null;
        try {
            clientes = em.createQuery("from Clientes c").getResultList();

        } catch (Exception e) {
            System.err.println(e);

        } finally {
            em.close();
        }
        return clientes;
    }
}
