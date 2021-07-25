package com.sapient.creditcardprocessor.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LuhnValidatorService {
    public boolean isValidCreditCardNumber(String cardNumber)
    {
        // int array for processing the cardNumber
        int[] cardIntArray=new int[cardNumber.length()];
        for(int i=0;i<cardNumber.length();i++)
        {
            char c= cardNumber.charAt(i);
            cardIntArray[i]=  Integer.parseInt(""+c);
        }
        for(int i=cardIntArray.length-2;i>=0;i=i-2)
        {
            int num = cardIntArray[i];
            num = num * 2;  // multiply the value at second index by 2
            if(num>9)
            {
                num = num%10 + num/10;  // check if num is greater than 9 , then add the digits
            }
            cardIntArray[i]=num;
        }
        int sum = Arrays.stream(cardIntArray).sum();
        if(sum%10==0)  // if number is multiple of 10, then it is valid otherwise invalid
        {
            return true;
        }
        return false;

    }
}
