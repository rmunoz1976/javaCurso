package com.proyecto.cts.zgeneral;

public enum EnumResult {
    mensajeError("unknown", "error", "saved", "saveError", "updated", "updateError", "deleted", "deleteError");
    private final String unknown;
    private final String error;
    private final String saved;
    private final String savedError;
    private final String updated;

    private final String updatedError;
    private final String deleted;

    private final String deleteError;

    EnumResult(String unknown, String error, String saved, String savedError, String updated, String updatedError, String deleted, String deleteError) {
        this.unknown = unknown;
        this.error = error;
        this.saved = saved;
        this.savedError = savedError;
        this.updated = updated;
        this.updatedError = updatedError;
        this.deleted = deleted;
        this.deleteError = deleteError;
    }

    public String getUnknown() {
        return unknown;
    }

    public String getError() {
        return error;
    }

    public String getSaved() {
        return saved;
    }

    public String getSavedError() {
        return savedError;
    }

    public String getUpdated() {
        return updated;
    }

    public String getUpdatedError() {
        return updatedError;
    }

    public String getDeleted() {
        return deleted;
    }

    public String getDeleteError() {
        return deleteError;
    }
}
