package com.unifasservice.utilities;

public class ValidateAddress {
    private static final String RECEIVER_REGEX = "^[a-zA-ZaàáảãạAÀÁẢÃẠăằắẳẵặĂẰẮẲẴẶâầấẩẫậÂẦẤẨẪẬeèéẻẽẹEÈÉẺẼẸêềếểễệÊỀẾỂỄỆiìíỉĩịIÌÍỈĨỊoòóỏõọOÒÓỎÕỌôồốổỗộÔỒỐỔỖỘơờớởỡợƠỜỚỞỠỢuùúủũụUÙÚỦŨỤưừứửữựƯỪỨỬỮỰyỳýỷỹỵYỲÝỶỸỴdđĐ ,.'-]+$";
    private static final String CONTACT_REGEX = "^[0-9\\-\\+]{10}$";
    private static final String STREET_REGEX = "^.+$";

    public static boolean validate(String type, String value){
        switch (type){
            case "RECEIVER":
                return value.matches(RECEIVER_REGEX);
            case "CONTACT":
                return value.matches(CONTACT_REGEX);
            case "STREET":
                return value.matches(STREET_REGEX);
        }
        return false;
    }


}
