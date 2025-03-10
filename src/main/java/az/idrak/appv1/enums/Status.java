package az.idrak.appv1.enums;

import az.idrak.appv1.exception.BusinessException;

import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Stream;

public enum Status implements Serializable {
    PENDING {
        @Override
        public Integer getCode() {
            return 1;
        }

        @Override
        public String getName() {
            return "PENDING";
        }
    },

    /********************************************************************/

    PAID {
        @Override
        public Integer getCode() {
            return 2;
        }

        @Override
        public String getName() {
            return "PAID";
        }
    },

    /********************************************************************/

    FAILED {
        @Override
        public Integer getCode() {
            return 3;
        }

        @Override
        public String getName() {
            return "FAILED";
        }
    };

    private Status() {
    }

    public abstract Integer getCode();

    public abstract String getName();

    public static Status lookUp(Integer id) throws BusinessException {
        for (Status status : values()) {
            if (status.getCode().equals(id)) {
                return status;
            }
        }
        throw new BusinessException(BusinessException.BusinessError.UNKNOWN_PROPERTY_TYPE);
    }

    public static Status of(Integer code) {
        return Stream.of(Status.values())
                .filter(status -> Objects.equals(status.getCode(), code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
