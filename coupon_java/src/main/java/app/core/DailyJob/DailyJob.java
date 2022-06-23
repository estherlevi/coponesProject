package app.core.DailyJob;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.core.services.JobService;

@Component
public class DailyJob extends Thread {
	
	@Autowired
	JobService service;
	
		@Override
		public void run() {
			System.out.println("======== task started");
			while (true) {
				try {
					System.out.println("check for expired coupons");
					service.deleteAllExpieredCoupons();
					TimeUnit.HOURS.sleep(24);
				} catch (InterruptedException e) {
					System.out.println("thread interrupted and will stop");
					break;
				}
				System.out.println(">>> from task");
			}
			System.out.println("======== Daily job-task ended");
		}

		@PostConstruct
		public void init() {
			this.start();
		}

		@PreDestroy
		public void destory() {
			this.interrupt();
		}

	}


