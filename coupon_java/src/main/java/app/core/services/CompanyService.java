package app.core.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.exceptions.CouponSystemException;

@Service
@Transactional(rollbackFor = CouponSystemException.class)
@Scope("prototype")
public class CompanyService extends ClientService {
	private int ID;

	public int getId() {
		return this.ID;
	}

	@Override
	public boolean login(String email, String password) throws CouponSystemException {
		Optional<Company> login = companyRepo.findByEmailAndPassword(email, password);

		if (login.isPresent()) {
			System.out.println("succesfuly logged into company with email: " + email + " and password: " + password);
			this.ID = login.get().getId();
			return true;
		} else {
			throw new CouponSystemException("wrong password or email enetered");

		}
	}

	public Coupon getCoupon(int couponId) throws CouponSystemException {
		Optional<Coupon> opt = this.couponRepo.findById(couponId);
		if (opt.isPresent()) {
			Coupon c = opt.get();
			c.setImage(getImageBlob(c.getId()));
			return c;
		} else {
			throw new CouponSystemException("coupon with id " + couponId + " not found");
		}

	}

	public void addCoupon(Coupon coupon, int CompanyID) throws CouponSystemException {
		try {
			String image = coupon.getImage();
			coupon.setImage(null);
			// saveDB
			Company company = getCompanyInfo(CompanyID);
			if (couponRepo.existsByDescriptionAndCompanyId(coupon.getDescription(), CompanyID)) {
				throw new CouponSystemException(
						"can't add coupon with same description as exsisting coupon for this company: " + CompanyID);
			}

			company.add(coupon);
			coupon = this.couponRepo.save(coupon);
			saveImage(image, coupon.getId());

		} catch (CouponSystemException e) {

			throw new CouponSystemException("failed to add coupon to company" + e.getMessage());
		}
	}

	private void saveImage(String image, int id) {
		File theDir = new File("C:\\couponsImages");
		if (!theDir.exists()) {
			theDir.mkdirs();
		}
		String fileName = "C:\\couponsImages\\" + id + ".txt";
		File myImage = new File(fileName);
		try {
			if (myImage.createNewFile()) {
				FileWriter myWriter = new FileWriter(fileName);
				myWriter.write(image);
				myWriter.close();
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private String getImageBlob(int id) {
		String fileName = "C:\\couponsImages\\" + id + ".txt";
		String data = "";
		try {
			File myObj = new File(fileName);
			if (myObj.exists()) {
				Scanner myReader = new Scanner(myObj);
				while (myReader.hasNextLine()) {
					data += myReader.nextLine();
				}
				myReader.close();
			}
			return data;

		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return data;
	}

	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		try {
			
			Coupon couponFromDb = getCoupon(coupon.getId());
			
			couponFromDb.setAmount(coupon.getAmount());
			couponFromDb.setDescription(coupon.getDescription());
			couponFromDb.setStartDate(coupon.getStartDate());
			couponFromDb.setEndDate(coupon.getEndDate());
			couponFromDb.setPrice(coupon.getPrice());
			couponFromDb.setCategory(coupon.getCategory());
			couponFromDb.setImage(coupon.getImage());
			couponFromDb.setTitle(coupon.getTitle());
			if (coupon.getImage() != null && !coupon.getImage().trim().isEmpty()) {
				System.out.println(coupon.getImage());
				saveImage(coupon.getImage(), couponFromDb.getId());
			}
			else {
				System.out.println("no image");
			}

		} catch (Exception e) {
			throw new CouponSystemException("update coupon failed" + e.getMessage());
		}

	}

	public void deleteCoupon(int couponId) {
		this.couponRepo.deleteById(couponId);
	}

	public List<Coupon> GetCouponByCategory(Category categoryName) throws CouponSystemException {

		Optional<List<Coupon>> opt = Optional.of(this.couponRepo.findByCategory(categoryName));
		if (opt.isPresent()) {
			System.out.println(opt);
			return opt.get();
		} else {
			throw new CouponSystemException("getCoupon failed - category " + categoryName + " not found");
		}
	}

	public List<Coupon> GetAllCoupons(int companyId) throws CouponSystemException {
		try {

			List<Coupon> allCoupons = this.couponRepo.findAllByCompanyId(companyId);
			System.out.println(allCoupons);
			return allCoupons;

		} catch (Exception e) {
			throw new CouponSystemException("no coupons found", e);
		}
	}

	public List<Coupon> GetAllCouponByMaxPrice(double maxPrice, int companyId) throws CouponSystemException {
		if (maxPrice > -1) {
			List<Coupon> allCouponsByMaxPrice = this.couponRepo.findByCompanyIdAndPriceLessThanEqual(companyId,
					maxPrice);
			System.out.println(allCouponsByMaxPrice);
			return allCouponsByMaxPrice;
		}
		throw new CouponSystemException("max price is equals or is less then o ");

	}

	public Company getCompanyInfo(int companyId) throws CouponSystemException {
		System.out.println("companyId " + companyId);
		Optional<Company> company = companyRepo.findById(companyId);

		if (company.isPresent()) {
			System.out.println(company);
			return company.get();
		}

		throw new CouponSystemException("company not found");
	}
}
