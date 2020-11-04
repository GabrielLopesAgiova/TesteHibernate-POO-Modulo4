/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.model.dao;

import classes.connection.ConnectionFactory;
import classes.model.bean.Veiculo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author glope
 */
public class VeiculoDAO {
    public void save(Veiculo veiculo) {

        EntityManager em = new ConnectionFactory().getConnection();

        try {
            em.getTransaction().begin();

            if (veiculo.getId() == null) {
                em.persist(veiculo);
            } else {
                em.merge(veiculo);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Veiculo remove(Integer id) {
        EntityManager em = new ConnectionFactory().getConnection();
        Veiculo veiculo = null;
        try {
            veiculo = em.find(Veiculo.class, id);
            em.getTransaction().begin();
            em.remove(veiculo);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "removido com sucesso");
        } catch (Exception e) {
            System.err.println(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return veiculo;
    }

    public Veiculo findById(Integer id) {

        EntityManager em = new ConnectionFactory().getConnection();
        Veiculo veiculo = null;

        try {
            veiculo = em.find(Veiculo.class, id);//passa a classe e o id

        } catch (Exception e) {
            System.err.println(e);

        } finally {
            em.close();

        }
        return veiculo;

    }

    public List<Veiculo> findAll() {
        EntityManager em = new ConnectionFactory().getConnection();
        List<Veiculo> veiculos = null;
        try {
            veiculos = em.createQuery("from Veiculo v").getResultList();

        } catch (Exception e) {
            System.err.println(e);

        } finally {
            em.close();
        }
        return veiculos;
    }

}
