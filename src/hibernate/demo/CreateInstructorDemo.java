package hibernate.demo;

import hibernate.entity.Course;
import hibernate.entity.Instructor;
import hibernate.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

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
            //create the objects
            Instructor tempInstructor = new Instructor("Will", "Smith", "smith@gmail.com");

            InstructorDetail tempDetail = new InstructorDetail("smith/youtube", "fishing");

            // assosiate the objects
            tempInstructor.setInstructorDetail(tempDetail);

            //start a transaction
            session.beginTransaction();

            // save the instructor
            session.save(tempInstructor);

            // commit transaction
            session.getTransaction().commit();
        }
        finally {

            session.close();
            factory.close();
        }
    }
}
