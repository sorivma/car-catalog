package com.example.carcatalog.dto.viewmodel;

import com.example.carcatalog.dto.ModelDTO;
import com.example.carcatalog.dto.OfferDTO;
import com.example.carcatalog.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Contains data for the view layer.
 */
@AllArgsConstructor
@Getter
@Setter
public class OfferViewModel {
    private OfferDTO offerDTO;
    private UserDTO userDTO;
    private ModelDTO modelDTO;
}
