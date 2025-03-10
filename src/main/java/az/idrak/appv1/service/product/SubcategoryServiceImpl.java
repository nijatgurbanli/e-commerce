package az.idrak.appv1.service.product;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.idrak.appv1.dto.SubcategoryDTO;
import az.idrak.appv1.entity.product.Subcategory;
import az.idrak.appv1.repository.SubcategoryRepository;

@Service
public class SubcategoryServiceImpl implements SubcategoryService {

    @Autowired
    private SubcategoryRepository subcategoryRepository;

    @Override
    public SubcategoryDTO save(SubcategoryDTO subcategoryDTO) {
        ModelMapper modelMapper = new ModelMapper();

        Subcategory subcategory = subcategoryRepository.save(modelMapper.map(subcategoryDTO, Subcategory.class));
        return modelMapper.map(subcategory, SubcategoryDTO.class);
    }
}