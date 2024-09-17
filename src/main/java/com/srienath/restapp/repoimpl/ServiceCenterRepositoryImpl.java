package com.srienath.restapp.repoimpl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import com.srienath.restapp.model.ServiceCenter;
import com.srienath.restapp.repo.ServiceCenterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ServiceCenterRepositoryImpl implements ServiceCenterRepository {

	 @PersistenceContext
	    private EntityManager entityManager;
	 
		private JavaMailSender mailSender;
				
	    public ServiceCenterRepositoryImpl(JavaMailSender mailSender) {
			super();
			this.mailSender = mailSender;
		}

		@Override
	    public ServiceCenter findById(int id) {
	        return entityManager.find(ServiceCenter.class, id);
	    }

	    @Override
	    public List<ServiceCenter> findAll() {
	        return entityManager.createQuery("from ServiceCenter", ServiceCenter.class).getResultList();
	    }

	    @Override
	    public void save(ServiceCenter serviceCenter) {
	        entityManager.persist(serviceCenter);
	    }

	    @Override
	    public void update(ServiceCenter serviceCenter) {
	        entityManager.merge(serviceCenter);
	    }

	    @Override
	    public void deleteById(int id) {
	        ServiceCenter serviceCenter = findById(id);
	        if (serviceCenter != null) {
	            entityManager.remove(serviceCenter);
	        }
	    }
		
		@SuppressWarnings("unchecked")
		@Override
		public List<ServiceCenter> getPendingRequest() {
			return entityManager.createQuery("FROM ServiceCenter sc where sc.status = 'Pending'").getResultList();
		}
	 
		@SuppressWarnings("unchecked")
		@Override
		public List<ServiceCenter> getApprovedRequest() {
			return entityManager.createQuery("FROM ServiceCenter sc where sc.status = 'Approved'").getResultList();
		}
	 
		@SuppressWarnings("unchecked")
		@Override
		public List<ServiceCenter> getRejectedRequest() {
			return entityManager.createQuery("FROM ServiceCenter sc where sc.status = 'Rejected'").getResultList();
		}
		
		@Override
		public Object getPendingCount() {
			Query query = entityManager.createQuery("select count(*) from ServiceCenter sc where sc.status = 'Pending'");
			return query.getSingleResult();
		}
		
		@Override
		public Object getApprovedCount() {
			Query query = entityManager.createQuery("select count(*) from ServiceCenter sc where sc.status = 'Approved'");
			return query.getSingleResult();
		}
	 
		@Override
		public Object getRejectedCount() {
			Query query = entityManager.createQuery("select count(*) from ServiceCenter sc where sc.status = 'Rejected'");
			return query.getSingleResult();
		}
	 
		
		@Override
		public boolean updateEmailApproved(int serviceCenterID) {
			ServiceCenter servicecenter= findById(serviceCenterID);
	 
			servicecenter.setStatus("Approved");
			
			ZoneId kolkataZoneId = ZoneId.of("Asia/Kolkata");
	        ZonedDateTime now = ZonedDateTime.now(kolkataZoneId);
	        Date formattedApprovalDate = Date.from(now.toInstant());
	        servicecenter.setApprovalDate(formattedApprovalDate);
	 
			try {
				if (servicecenter != null) {
					entityManager.persist(servicecenter);
	 
					String email = servicecenter.getEmail();
					String username = servicecenter.getAdmin().getUsername();
					String centername = servicecenter.getServiceCenterName();

	 
					SimpleMailMessage message = new SimpleMailMessage();
					message.setFrom("your-email@example.com");
					message.setTo(email);
					message.setSubject("Welcome to AutoCare Hub - Registration Confirmation");

				    String emailBody = String.format(
				        "Dear" + username+ "%n%n" +
				        "Congratulations on completing your registration with AutoCare Hub!%n%n" +
				        "We are pleased to inform you that your Service Center - " + centername + "'s Registration has been approved.%n" +
				        "You can now proceed with setting up your Service Center and providing car services to our customers.%n%n" +
				        "Here are the next steps for you to follow:%n" +
				        "1. **Complete Service Center Setup**: Please proceed to set up your service center.%n" +
				        "2. **Provide Services**: Start offering car services to our valued customers.%n%n" +
				        "Should you have any questions or need further assistance, please do not hesitate to reach out to us at support@autocarehub.com.%n%n" +
				        "Thank you for your attention and for being a part of our network! We look forward to a successful collaboration.%n%n" +
				        "Best regards,%n" +
				        "The AutoCare Hub Team%n" +
				        "Email: support@autocarehub.com%n" +
				        "Website: www.autocarehub.com"
				    );
	 
				    message.setText(emailBody);
			        mailSender.send(message);
			        return true;
				}
	 
			} catch (Exception e) {
				System.err.println(e);
			}
			return false;
		}
	 
		@Override
		public boolean updateEmailRejected(int serviceCenterID) {
			ServiceCenter servicecenter = findById(serviceCenterID);
			servicecenter.setStatus("Rejected");
			
			ZoneId kolkataZoneId = ZoneId.of("Asia/Kolkata");
	        ZonedDateTime now = ZonedDateTime.now(kolkataZoneId);
	        Date formattedApprovalDate = Date.from(now.toInstant());
	        servicecenter.setApprovalDate(formattedApprovalDate);
			
			try {
				if (servicecenter != null) {
					String email = servicecenter.getEmail();
					String username = servicecenter.getAdmin().getUsername();
					String centername = servicecenter.getServiceCenterName();

	 
					SimpleMailMessage message = new SimpleMailMessage();
					message.setFrom("your-email@example.com");
					message.setTo(email);
					message.setSubject("Welcome to AutoCare Hub - Registration Declined");

				    String emailBody = String.format(
				        "Dear" + username+ "%n%n" +
				        "Thank you for your interest in joining AutoCare Hub and for completing your registration for the Service Center - " + centername + "%n%n" +
				        "We regret to inform you that, after careful consideration, your registration has not been approved at this time.%n%n" +
				        "We encourage you to review the following points and consider reapplying in the future:%n" +
				        "1. **Review Application Requirements**: Ensure all required documents and information are complete and accurate.%n" +
				        "2. **Address Feedback**: If you received any feedback or reasons for the declination, address those concerns before reapplying.%n%n" +
				        "If you have any questions or need further clarification, please do not hesitate to contact us at support@autocarehub.com. We are here to assist you and provide guidance on how you can strengthen your application.%n%n" +
				        "Thank you for your understanding and for your interest in being a part of AutoCare Hub. We wish you the best in your future endeavors.%n%n" +
				        "Best regards,%n" +
				        "The AutoCare Hub Team%n" +
				        "Email: support@autocarehub.com%n" +
				        "Website: www.autocarehub.com"
				    );
	 
				    message.setText(emailBody);
			        mailSender.send(message);
			        return true;
				} 
			}catch (MailException e) {
				e.printStackTrace();
			}
			return false;
		}

}
