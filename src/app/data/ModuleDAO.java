package app.data;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ModuleDAO {

    public static Module retrieveById(int id) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        Module module = em.find(Module.class, id);

        // Close the entity manager
        em.close();

        return module;
    }

    public static Module create(String nom, List<Groupe> groupes) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // create
        em.getTransaction().begin();

        // create new groupe
        Module module = new Module();
        module.setNom(nom);
        module.setGroupes(groupes);
        em.persist(module);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return module;
    }

    public static Module update(Module module) {

        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        // Attacher une entité persistante (etudiant) à l’EntityManager courant  pour réaliser la modification
        em.merge(module);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();

        return module;
    }

    public static List<Module> getAll() {
        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        // Recherche
        Query q = em.createQuery("SELECT m FROM Module m");

        @SuppressWarnings("unchecked")
        List<Module> listModule = q.getResultList();

        return listModule;
    }

    public static void remove(Module module) {
        // Creation de l'entity manager
        EntityManager em = GestionFactory.factory.createEntityManager();

        //
        em.getTransaction().begin();

        module = em.find(Module.class, module.getId());
        em.remove(module);

        // Commit
        em.getTransaction().commit();

        // Close the entity manager
        em.close();
    }
}
