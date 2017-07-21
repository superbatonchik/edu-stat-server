package ru.cmo.edu.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cmo.edu.data.dto.RegionCoreDto;
import ru.cmo.edu.data.dto.RegionFormDataCoreDto;
import ru.cmo.edu.data.resource.RegionResource;
import ru.cmo.edu.rest.security.Role;
import ru.cmo.edu.service.FormDataService;
import ru.cmo.edu.service.RegionService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by to on 05.06.2017.
 */

@RestController
@RequestMapping(value = "/view/regionformdata")
public class RegionFormDataController extends BaseController {

    Logger logger = LoggerFactory.getLogger(RegionFormDataController.class);

    @Autowired
    private FormDataService formDataService;
    @Autowired
    private RegionService regionService;

    @PreAuthorize("hasAnyRole('region', 'ministry')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity index(@RequestParam int id,
                                @RequestParam(required = false, defaultValue = "false") boolean isArchived) {
        Role role = getRole();
        if (role == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Link navLink = linkTo(RegionFormDataController.class).withSelfRel();
        switch(role) {
            case ministry:
                navLink = linkTo(methodOn(RegionFormDataController.class).getRegionList()).withSelfRel();
                break;
            case region:
                navLink = linkTo(methodOn(RegionFormDataController.class).getFormList(id, isArchived)).withSelfRel();
                break;
            case municipality:
            case edu:
                throw new AccessDeniedException("Edu, municipality can not access municipality forms");
        }
        return ResponseEntity.ok(navLink);
    }

    @PreAuthorize("hasAnyRole('ministry')")
    @RequestMapping(value = "/region", method = RequestMethod.GET)
    public ResponseEntity getRegionList() {
        List<RegionCoreDto> dtos = regionService.getAllDto(RegionCoreDto.class);
        List<RegionResource> resources = dtos.stream().map(t ->
        {
            RegionResource resource = new RegionResource(t);
            List<Link> navigateToFormLinks = new ArrayList<>();
            navigateToFormLinks.add(linkTo(methodOn(RegionFormDataController.class).getFormList(t.getId(), false)).withRel("actual"));
            navigateToFormLinks.add(linkTo(methodOn(RegionFormDataController.class).getFormList(t.getId(), true)).withRel("archived"));
            resource.add(navigateToFormLinks);
            return resource;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @PreAuthorize("hasAnyRole('region', 'ministry')")
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public ResponseEntity getFormList(@RequestParam int regionId,
                                      @RequestParam boolean isArchived) {
        List<RegionFormDataCoreDto> dtos = formDataService.getRegionFormDataDto(regionId, isArchived);
        return ResponseEntity.ok(dtos);
    }
}
