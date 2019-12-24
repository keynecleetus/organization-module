package com.revature.organizations.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.organizations.dao.OrganizationDAO;
import com.revature.organizations.dao.OrganizationDAOImpl;
import com.revature.organizations.dto.ManageOrganizationDTO;
import com.revature.organizations.dto.MessageConstant;
import com.revature.organizations.dto.OrganizationDTO;
import com.revature.organizations.exceptions.DBException;
import com.revature.organizations.exceptions.ServiceException;
import com.revature.organizations.exceptions.ValidatorException;
import com.revature.organizations.model.OrganizationDetails;
import com.revature.organizations.validator.OrganizationServiceValidator;

@Service
public class OrganizationService {
	
	
	private  final OrganizationDAO organizationDAOImpl;
	
	private final OrganizationServiceValidator organizationServiceValidator;

	@Autowired
	public OrganizationService(OrganizationDAOImpl organizationDAOImpl,OrganizationServiceValidator organizationServiceValidator) {
		super();
		this.organizationDAOImpl = organizationDAOImpl;
		this.organizationServiceValidator=organizationServiceValidator;
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationService.class);

	public Boolean createOrganization(final OrganizationDTO organizationDTO) throws ServiceException {
		Boolean isStatus = true;

		try {
			//organizationServiceValidator.organizationService(organizationDTO);

			isStatus = organizationDAOImpl.createOrganization(organizationDTO);

		} catch (DBException e) {
			LOGGER.error("service Exception in creating an organization", e);
			throw new ServiceException(e.getMessage(),e);
		}
		return isStatus;

	}

	public List<OrganizationDetails> getAll() throws ServiceException {
		List<OrganizationDetails> organizationDTO = null;
		try {

			organizationDTO = organizationDAOImpl.getAll();
			if (organizationDTO.isEmpty()) {
				throw new ServiceException(MessageConstant.ORGANIZATION_LIST);
			}
		} catch (DBException e) {
			LOGGER.error("Service Exception in list", e);
			throw new ServiceException(e.getMessage(),e);
		}

		return organizationDTO;
	}
	public Boolean manageOrganization(ManageOrganizationDTO organization)throws ServiceException
	{
		Boolean isStatus=false;
		try {
			isStatus=organizationDAOImpl.manageOrganization(organization);
		} catch (DBException e) {
			LOGGER.error("Service Exception in managing the organization", e);
			throw new ServiceException(e.getMessage(),e);
		}
		System.out.println("service"+isStatus);
		return isStatus;
		
	}

	public Boolean deleteLogo(int organizationId)throws ServiceException {
		Boolean isStatus=false;
		try {
			isStatus=organizationDAOImpl.deleteLogo(organizationId);
		} catch (DBException e) {
			LOGGER.debug("Service Exception in deleting the logo and favicon", e);
			throw new ServiceException(e.getMessage(),e);
		}
		System.out.println("service"+isStatus);
		return isStatus;
		
	}


}
