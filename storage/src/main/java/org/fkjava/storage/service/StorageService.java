package org.fkjava.storage.service;

import java.io.InputStream;

import org.fkjava.storage.domain.FileInfo;
import org.springframework.data.domain.Page;

public interface StorageService {

	void save(FileInfo info, InputStream in);

	Page<FileInfo> findFiles(String keyword, Integer number);

}
