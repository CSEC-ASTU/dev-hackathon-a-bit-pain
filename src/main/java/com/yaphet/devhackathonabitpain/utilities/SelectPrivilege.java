package com.yaphet.devhackathonabitpain.utilities;

import com.yaphet.devhackathonabitpain.models.Privilege;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SelectPrivilege {
    private List<Privilege> selectedPrivileges;
}
