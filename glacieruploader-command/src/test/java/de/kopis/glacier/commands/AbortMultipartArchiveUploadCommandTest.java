package de.kopis.glacier.commands;

/*-
 * #%L
 * glacieruploader-command
 * %%
 * Copyright (C) 2012 - 2017 Carsten Ringe
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;
import com.amazonaws.services.glacier.model.AbortMultipartUploadRequest;
import com.amazonaws.services.glacier.model.AbortMultipartUploadResult;
import joptsimple.OptionSet;

public class AbortMultipartArchiveUploadCommandTest extends AbstractCommandTest {
    @Test
    public void exec() throws Exception {
        expect(client.abortMultipartUpload(isA(AbortMultipartUploadRequest.class))).andReturn(new AbortMultipartUploadResult()).times(1);
        replay(client);

        final OptionSet options = optionParser.parse("--abort-upload", UUID.randomUUID().toString());
        final AbortMultipartArchiveUploadCommand command = new AbortMultipartArchiveUploadCommand(client, sqs, sns);

        assertTrue(command.valid(options, optionParser));
        command.exec(options, optionParser);

        verify(client);
    }

}
