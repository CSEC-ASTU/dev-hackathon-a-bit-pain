package com.yaphet.devhackathonabitpain.utilities;

import com.yaphet.devhackathonabitpain.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SelectedRole {
    public List<Role> selectedRoles;
}
