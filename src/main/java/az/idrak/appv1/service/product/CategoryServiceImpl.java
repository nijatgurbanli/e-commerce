package az.idrak.appv1.service.product;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.idrak.appv1.dto.CategoryDTO;
import az.idrak.appv1.dto.TypeDTO;
import az.idrak.appv1.entity.product.Category;
import az.idrak.appv1.entity.product.Type;
import az.idrak.appv1.repository.CategoryRepository;
import az.idrak.appv1.repository.TypeRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        ModelMapper modelMapper = new ModelMapper();

        Category category = categoryRepository.save(modelMapper.map(categoryDTO, Category.class));
        return modelMapper.map(category, CategoryDTO.class);
    }
}