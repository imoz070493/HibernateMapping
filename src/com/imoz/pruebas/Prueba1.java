package com.imoz.pruebas;

import com.imoz.pojos.Curso;
import com.imoz.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Prueba1 {

    public static void main(String[] args) {
        
        //Obtener Cursos
        /*Prueba1 p = new Prueba1();
        for(Curso c:p.obtenerCursos()){
            System.out.println(c);
        }*/
        
        //Crear curso
        /*Curso c1 = new Curso();
        c1.setId("C01");
        c1.setNombre("Java");
        c1.setCreditos(5);
        
        Curso c2 = new Curso();
        c2.setId("C02");
        c2.setNombre("PHP");
        c2.setCreditos(5);
        
        Curso c3 = new Curso();
        c3.setId("C03");
        c3.setNombre("Pyton");
        c3.setCreditos(3);
        
        Prueba1 p = new Prueba1();
        p.crearCurso(c1);
        p.crearCurso(c2);
        p.crearCurso(c3);
        
        p.obtenerCursos();
        */

        //Eliminar Curso
        /*String id = "c03";
        Prueba1 p = new Prueba1();
        p.eliminarCurso(id);
        
        for(Curso c:p.obtenerCursos()){
            System.out.println(c);
        }*/
        
        //Actualizar Curso
        /*Curso c = new Curso();
        c.setId("C02");
        c.setNombre("PHP and Mysql");
        c.setCreditos(6);
        
        Prueba1 p = new Prueba1();
        p.updateCurso(c);*/
        
        //Buscar por Id Curso
        String id = "c02";
        Prueba1 p = new Prueba1();
        Curso c = p.findId(id);
        System.out.println(c);
        
    }
    
    
    public List<Curso> obtenerCursos(){
        List<Curso> arreglo = new ArrayList<>();
        try{
            SessionFactory sesion = HibernateUtil.getSessionFactory();
            Session session = sesion.openSession();
            Transaction tx = session.beginTransaction();
            //Aqui hacemos la magia
            
            Query q = session.createQuery("from Curso");
            arreglo = q.list();
            //Aqui termina la magia
            tx.commit();
            session.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }        
        return arreglo;
    }
    
    public void crearCurso(Curso curso) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(curso);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void eliminarCurso(String cursoid) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Curso curso = (Curso) session.load(Curso.class, cursoid);
            session.delete(curso);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public void updateCurso(Curso curso) {
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(curso);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }
    
    public Curso findId(String cursoid) {
        Curso curso = null;
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            String queryString = "from Curso where id = :id";
            Query query = session.createQuery(queryString);
            query.setString("id", cursoid);
            curso = (Curso) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return curso;
    }
      
    
}
