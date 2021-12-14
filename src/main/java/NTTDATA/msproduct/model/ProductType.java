package NTTDATA.msproduct.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("productType")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductType {
    @Id
    private String id;
    private String type;
}
