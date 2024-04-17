package com.example.sakilademo.dto.input;

import jakarta.validation.groups.Default;

public class ValidationGroup {
    public interface Create extends Default{}
    public interface Update extends Default{}

}
