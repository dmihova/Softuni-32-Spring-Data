package su.productsshop.entiies.category;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name ="categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportCategoriesXMLDTO {

    @XmlElement(name = "category")
    private List<CategoryNameDTO> categoryNameDTOList;

    public ImportCategoriesXMLDTO() {
        this.categoryNameDTOList=new ArrayList<>();
    }


    public ImportCategoriesXMLDTO(List<CategoryNameDTO> categoryNameDTOList) {
        this.categoryNameDTOList = categoryNameDTOList;
    }

    public List<CategoryNameDTO> getCategoryNameDTOList() {
        return categoryNameDTOList;
    }

    public void setCategoryNameDTOList(List<CategoryNameDTO> categoryNameDTOList) {
        this.categoryNameDTOList = categoryNameDTOList;
    }
}
