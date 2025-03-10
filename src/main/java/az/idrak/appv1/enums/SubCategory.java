package az.idrak.appv1.enums;
//package az.idrak.appV1.enums;
//
//import az.idrak.appV1.exception.BusinessException;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//
//import java.util.Objects;
//import java.util.stream.Stream;
//
//@Getter
//@AllArgsConstructor
//public enum SubCategory {
//
//    MAN(1001, "MAN"),
//    WOMAN(1002, "WOMAN"),
//
//    BEDROOM(1003, "BEDROOM"),
//    OFFICE(1004, "OFFICE"),
//    LIVING_ROOM(1005, "LIVING&ROOM"),
//    KITCHEN_DINNING(1006, "KITCHEN&DINNING"),
//
//    LAPTOP_COMPUTER(1007, "LAPTOP&COMPUTER"),
//    DIGITAL_CAMERAS(1008, "DIGITAL CAMERAS"),
//    TV_VIDEO(1009, "TV&VIDEO"),
//    SMARTPHONE(1010, "SMARTPHONE");
//
//
//    private final Integer id;
//
//    private final String name;
//
//    public static SubCategory lookUp(Integer id) throws BusinessException {
//        for (SubCategory category : values()) {
//            if (category.id.equals(id)) {
//                return category;
//            }
//        }
//        throw new BusinessException(BusinessException.BusinessError.UNKNOWN_DEVICE_TYPE);
//    }
//
//    public static SubCategory of(Integer id) {
//        return Stream.of(SubCategory.values())
//                .filter(p -> Objects.equals(p.getId(), id))
//                .findFirst()
//                .orElseThrow(IllegalArgumentException::new);
//    }
//}
