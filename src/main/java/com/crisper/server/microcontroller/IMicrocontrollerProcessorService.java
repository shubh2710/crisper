package com.crisper.server.microcontroller;

import com.crisper.server.microcontroller.model.MicrocontrollerRequest;
import com.crisper.server.microcontroller.model.MicrocontrollerResponse;

public interface IMicrocontrollerProcessorService {
    public MicrocontrollerResponse process(MicrocontrollerRequest request);
}
