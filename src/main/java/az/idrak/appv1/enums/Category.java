package az.idrak.appv1.enums;
//package az.idrak.appV1.enums;
//
//import az.idrak.appV1.exception.BusinessException;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//
//import java.io.Serializable;
//import java.util.Objects;
//import java.util.stream.Stream;
//
//@Getter
//@AllArgsConstructor
//public enum Category implements Serializable {
//
//
//    FASHION(101, "FASHION"),
//    HOME_GARDEN(102, "HOME&GARDEN"),
//    ELECTRONICS(103, "ELECTRONICS"),
//    FURNITURE(104, "FURNITURE");
//
////    HEALTHY_BEAUTY(105,"HEALTHY&BEAUTY"),
////    GIFT_IDEAS(106,"GIFT&IDEAS"),
////    TOY_GAMES(107,"TOY&GAMES"),
////    COOKING(108,"TOY&GAMES");
//
//
//    private final Integer id;
//
//    private final String name;
//
//    public static Category lookUp(Integer id) throws BusinessException {
//        for (Category category : values()) {
//            if (category.id.equals(id)) {
//                return category;
//            }
//        }
//        throw new BusinessException(BusinessException.BusinessError.UNKNOWN_DEVICE_TYPE);
//    }
//
//    public static Category of(Integer id) {
//        return Stream.of(Category.values())
//                .filter(p -> Objects.equals(p.getId(), id))
//                .findFirst()
//                .orElseThrow(IllegalArgumentException::new);
//    }
//}
