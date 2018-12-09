package org.fkjava.storage.service;

import java.io.FileNotFoundException;
import java.io.InputStream;

import org.fkjava.common.data.domain.Result;
import org.fkjava.storage.domain.FileInfo;
import org.springframework.data.domain.Page;

public interface StorageService {

	FileInfo save(FileInfo info, InputStream in);

	Page<FileInfo> findFiles(String keyword, Integer number);

	FileInfo findById(String id);

	InputStream getFileContent(FileInfo fi) throws FileNotFoundException;

	Result deleteFile(String id);

	FileInfo findByFingerprint(String fingerprint);
}
