package hibernate.demo;

import hibernate.entity.Course;
import hibernate.entity.Instructor;
import hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourcesDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .buildSessionFactory();
        // create a session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();
            Instructor tempInstructor = session.get(Instructor.class, 1);
            Course tempCourse1 = new Course("How to kill a witch");
            Course tempCourse2 = new Course("How to kill a Rian");
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);
            session.save(tempCourse1);
            session.save(tempCourse2);
            session.getTransaction().commit();

        }
        finally {

            session.close();
            factory.close();
        }
    }
}
