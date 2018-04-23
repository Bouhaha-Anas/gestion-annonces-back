package com.epi.pfa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epi.pfa.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>
{
	List<Notification> findByDateExpiration(Date date);
}
